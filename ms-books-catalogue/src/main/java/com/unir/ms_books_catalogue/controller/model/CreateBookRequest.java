package com.unir.ms_books_catalogue.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {

	private String title;
	private String author;
	private LocalDate publication_date;
	private String category;
	private String isbn;
	private Integer rating;
	private Boolean visible;
}
