package com.example.DTO;

import com.example.models.Book;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookResponse {
    private Long bookId;
    private String id;
    private String title;
    private List<String> authors;
    private String publishedDate;
    private String description;
    private String imageLinks;
    private String buyLink;
    private Book.ListPrice listPrice;
    private String previewLink;
    private List<Book.IndustryIdentifier> industryIdentifiers;
    private int pageCount;
}
