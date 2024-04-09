package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "industry_identifier")
public class IndustryIdentifier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String identifier;
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "bookId")
    private BookEntity book;
}
