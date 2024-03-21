package com.example.DTO;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Builder
@Data
public class AuthorRequest {
    private String name;
    private Date dateOfBirth;
    private String preview;
    private double rating;
}
