package com.unir.ms_books_catalogue.service;

import java.time.LocalDate;
import java.util.List;

import com.unir.ms_books_catalogue.data.model.Book;
import com.unir.ms_books_catalogue.controller.model.CreateBookRequest;

public interface BooksService {
	
	List<Book> getBooks();

	Book getBookById(String BookId);

	Boolean removeBook(String BookId);

	Book updateBook(String BookId, CreateBookRequest request);

	Book patchBook(String BookId, CreateBookRequest request);

	Book createBook(CreateBookRequest request);

	Boolean areBooksAvailable(List<Long> bookIds);

	List<Book> searchBooks(
			String title,
			String author,
			LocalDate publication_date,
			String category,
			String isbn,
			Integer rating,
			Boolean visible
	);
}
