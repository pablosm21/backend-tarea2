package com.unir.ms_books_catalogue.data;

import java.util.List;

import com.unir.ms_books_catalogue.data.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByVisibleTrue();

}
