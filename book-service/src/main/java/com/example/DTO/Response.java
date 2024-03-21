package com.example.DTO;

import com.example.models.Author;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

/**
 * Ответ клиенту.
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Response {
    private Long id;
    private UUID uuid;
    private String title;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;
    private String publishingOffice;
    private String series;
    private int price;
    private String isbn;
    private int pages;
    private AuthorResponse author;
}
