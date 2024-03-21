package com.example.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Запрос от клиента.
 */
@Builder
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Request {
    private String title;
    private String description;
    private Date releaseDate;
    private String publishingOffice;
    private String series;
    private int price;
    private String isbn;
    private int pages;
    private AuthorRequest author;
}
