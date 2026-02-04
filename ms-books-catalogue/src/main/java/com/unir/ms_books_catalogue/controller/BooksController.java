package com.unir.ms_books_catalogue.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.unir.ms_books_catalogue.controller.model.BookAvailabilityRequest;
import com.unir.ms_books_catalogue.controller.model.CreateBookRequest;
import com.unir.ms_books_catalogue.data.model.Book;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BooksController {

	private final com.unir.ms_books_catalogue.service.BooksService service;

	/**
	 * Posibles respuestas:
	 *  - 200 OK: devuelve la lista de libros visibles.
	 *  - 500 Internal Server Error: error inesperado en el servidor.
	 * @return lista de libros visibles.
	 */
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks(@RequestHeader Map<String, String> headers) {

		log.info("headers: {}", headers);
		List<Book> books = service.getBooks();
		return ResponseEntity.ok(books);
	}

	/**
	 * Posibles respuestas:
	 *  - 200 OK: el libro existe y está visible.
	 *  - 404 Not Found: el libro no existe o está oculto.
	 *  - 400 Bad Request: el identificador no tiene un formato válido.
	 *  - 500 Internal Server Error: error inesperado.
	 * @param bookId identificador del libro.
	 * @return libro visible si existe.
	 */
	@GetMapping("/books/{bookId}")
	public ResponseEntity<Book> getBookById(@PathVariable String bookId) {

		log.info("Request received for book {}", bookId);
		Book book = service.getBookById(bookId);

		return book != null
				? ResponseEntity.ok(book)
				: ResponseEntity.notFound().build();

	}

	/**
	 * Posibles respuestas:
	 *  - 201 Created: el libro creado correctamente.
	 *  - 400 Bad Request: los datos enviados no son válidos.
	 *  - 500 Internal Server Error: error inesperado.
	 * @param request datos del libro a crear.
	 * @return libro creado.
	 */
	@PostMapping("/books")
	public ResponseEntity<Book> createBook(@RequestBody CreateBookRequest request) {

		Book createdBook = service.createBook(request);

		return createdBook != null
				? ResponseEntity.status(HttpStatus.CREATED).body(createdBook)
				: ResponseEntity.badRequest().build();

	}

	/**
	 * Posibles respuestas:
	 *  - 200 OK: el libro se ha modificado correctamente.
	 *  - 400 Bad Request: los datos enviados no son válidos.
	 *  - 404 Not Found: el libro no existe.
	 *  - 500 Internal Server Error: error inesperado.
	 *
	 * @param bookId identificador del libro a modificar.
	 * @param request nuevos datos del libro.
	 * @return libro actualizado.
	 */
	@PutMapping("/books/{bookId}")
	public ResponseEntity<Book> updateBook(
			@PathVariable String bookId,
			@RequestBody CreateBookRequest request) {

		Book updated = service.updateBook(bookId, request);

		return updated != null
				? ResponseEntity.ok(updated)
				: ResponseEntity.notFound().build();
	}

	/**
	 * Posibles respuestas:
	 *  - 200 OK: el libro se ha actualizado parcialmente.
	 *  - 400 Bad Request: los datos enviados no son válidos.
	 *  - 404 Not Found: el libro no existe.
	 *  - 500 Internal Server Error: error inesperado.
	 * @param bookId identificador del libro a modificar.
	 * @param request campos del libro a actualizar.
	 * @return libro actualizado.
	 */
	@PatchMapping("/books/{bookId}")
	public ResponseEntity<Book> patchBook(
			@PathVariable String bookId,
			@RequestBody CreateBookRequest request) {

		Book updated = service.patchBook(bookId, request);

		return updated != null
				? ResponseEntity.ok(updated)
				: ResponseEntity.notFound().build();
	}

	/**
	 * Posibles respuestas:
	 *  - 200 OK: el libro se ha eliminado correctamente.
	 *  - 404 Not Found: el libro no existe.
	 *  - 400 Bad Request: el identificador no es válido.
	 *  - 500 Internal Server Error: error inesperado.
	 * @param bookId identificador del libro a eliminar.
	 * @return respuesta sin contenido.
	 */
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable String bookId) {

		Boolean removed = service.removeBook(bookId);

		return Boolean.TRUE.equals(removed)
				? ResponseEntity.ok().build()
				: ResponseEntity.notFound().build();
	}

	/**
	 * Posibles respuestas:
	 *  - 200 OK: todos los libros existen y están disponibles.
	 *  - 409 Conflict: alguno de los libros no existe o no está disponible.
	 *  - 400 Bad Request: la lista de identificadores es inválida.
	 *  - 500 Internal Server Error: error inesperado.
	 * @param request lista de identificadores de libros a comprobar.
	 * @return respuesta indicando si los libros están disponibles.
	 */
	@PostMapping("/books/availability")
	public ResponseEntity<Void> checkAvailability(@RequestBody BookAvailabilityRequest request) {

		boolean available = service.areBooksAvailable(request.getBookIds());

		return available
				? ResponseEntity.ok().build()
				: ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

	/**
	 * Recibe todos los parámetros que componen a un libro individualmente para revisar cada uno
	 * Posibles respuestas:
	 *  - 200 OK: todos los libros existen y están disponibles.
	 *  - 409 Conflict: alguno de los libros no existe o no está disponible.
	 * @return Devuelve la búsqueda de elementos
	 * http://localhost:8088/books/search?author=pako&visible=true
	 */
	@GetMapping("/books/search")
	public ResponseEntity<List<Book>> searchBooks(
			@RequestParam(required = false) String author,
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String category,
			@RequestParam(required = false) LocalDate publication_date,
			@RequestParam(required = false) String isbn,
			@RequestParam(required = false) Integer rating,
			@RequestParam(required = false) Boolean visible
	){

		List<Book> books = service.searchBooks(title,author,publication_date,category,isbn,rating,visible);

		return books != null
				? ResponseEntity.ok(books)
				: ResponseEntity.notFound().build();
	}

}
