
package com.unir.ms_books_catalogue.service;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.ms_books_catalogue.data.BookRepository;
import com.unir.ms_books_catalogue.data.model.Book;
import com.unir.ms_books_catalogue.controller.model.CreateBookRequest;

@Service
public class BooksServiceImpl implements BooksService {

	@Autowired
	private BookRepository repository;

	@Override
	public List<Book> getBooks() {
		return repository.findByVisibleTrue();
	}

	@Override
	public Book getBookById(String bookId) {
		return repository.findById(Long.valueOf(bookId)).filter(Book::getVisible).orElse(null);
	}

	@Override
	public Book updateBook(String bookId, CreateBookRequest request) {

		Book existingBook = repository.findById(Long.valueOf(bookId)).orElse(null);
		if (existingBook == null) {
			return null;
		}

		if (request == null
				|| !StringUtils.hasLength(request.getTitle())
				|| !StringUtils.hasLength(request.getAuthor())
				|| !StringUtils.hasLength(request.getCategory())
				|| request.getPublication_date() == null
				|| !StringUtils.hasLength(request.getIsbn())
				|| request.getRating() == null
				|| request.getVisible() == null) {
			return null;
		}

		existingBook.setTitle(request.getTitle());
		existingBook.setAuthor(request.getAuthor());
		existingBook.setCategory(request.getCategory());
		existingBook.setPublication_date(request.getPublication_date());
		existingBook.setIsbn(request.getIsbn());
		existingBook.setRating(request.getRating());
		existingBook.setVisible(request.getVisible());

		return repository.save(existingBook);
	}

	@Override
	public Book patchBook(String bookId, CreateBookRequest request) {

		Book book = repository.findById(Long.valueOf(bookId)).orElse(null);
		if (book == null || request == null) {
			return null;
		}

		if (StringUtils.hasLength(request.getTitle())) {
			book.setTitle(request.getTitle());
		}
		if (StringUtils.hasLength(request.getAuthor())) {
			book.setAuthor(request.getAuthor());
		}
		if (StringUtils.hasLength(request.getCategory())) {
			book.setCategory(request.getCategory());
		}
		if (request.getPublication_date() != null) {
			book.setPublication_date(request.getPublication_date());
		}
		if (StringUtils.hasLength(request.getIsbn())) {
			book.setIsbn(request.getIsbn());
		}
		if (request.getRating() != null) {
			book.setRating(request.getRating());
		}
		if (request.getVisible() != null) {
			book.setVisible(request.getVisible());
		}

		return repository.save(book);
	}

	@Override
	public Boolean removeBook(String bookId) {

		Book book = repository.findById(Long.valueOf(bookId)).orElse(null);

		if (book != null) {
			repository.delete(book);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public Book createBook(CreateBookRequest request) {

		if (request != null
				&& StringUtils.hasLength(request.getTitle())
				&& StringUtils.hasLength(request.getAuthor())
				&& StringUtils.hasLength(request.getCategory())
				&& request.getPublication_date() != null
				&& StringUtils.hasLength(request.getIsbn())
				&& request.getRating() != null
				&& request.getVisible() != null) {

			Book book = Book.builder()
					.title(request.getTitle())
					.author(request.getAuthor())
					.category(request.getCategory())
					.publication_date(request.getPublication_date())
					.isbn(request.getIsbn())
					.rating(request.getRating())
					.visible(request.getVisible())
					.build();

			return repository.save(book);
		}

		return null;
	}

	@Override
	public Boolean areBooksAvailable(List<Long> bookIds) {

		if (bookIds == null || bookIds.isEmpty()) {
			return false;
		}

		List<Book> books = repository.findAllById(bookIds);

		// Si no devuelve el mismo número, alguno no existe
		if (books.size() != bookIds.size()) {
			return false;
		}

		return books.stream().allMatch(Book::getVisible);
	}

	@Override
	public List<Book> searchBooks (
			String title,
			String author,
			LocalDate publication_date,
			String category,
			String isbn,
			Integer rating,
			Boolean visible
	){
	List<Book> books = repository.findAll();

	return books.stream()
			.filter(b -> title == null || b.getTitle().toLowerCase().contains(title.toLowerCase()))
			.filter(b -> author == null || b.getAuthor().toLowerCase().contains(author.toLowerCase()))
			.filter(b -> publication_date == null ||  b.getPublication_date().equals(publication_date))
			.filter(b -> category == null ||  b.getCategory().toLowerCase().contains(category.toLowerCase()))
			.filter(b -> isbn == null ||  b.getIsbn().contains(isbn))
			.filter(b -> rating == null ||  b.getRating().equals(rating))
			.filter(b -> visible == null ||   b.getVisible() == visible )
			.toList();
	}
}
