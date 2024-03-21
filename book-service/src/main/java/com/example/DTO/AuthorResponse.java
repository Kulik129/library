package com.example.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthorResponse {
    private Long id;
    private UUID uuid;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String preview;
    private double rating;
}
