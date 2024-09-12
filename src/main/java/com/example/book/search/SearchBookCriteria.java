package com.example.book.search;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.LocalDate;

@Data
public class SearchBookCriteria {
    private String title;
    private String author;
    private String publisher;
    private LocalDate startDate;
    private LocalDate endDate;
    private String direction = "asc";
    private String sortBy = "id";
}
