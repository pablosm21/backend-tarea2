package com.unir.ms_books_catalogue.controller.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookAvailabilityRequest {
	private List<Long> bookIds;
}

