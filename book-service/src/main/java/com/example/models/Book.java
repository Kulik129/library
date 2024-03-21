package com.example.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String title;
    private String description;
    @Column(name = "release_date")
    private Date releaseDate;
    @Column(name = "publishing_office")
    private String publishingOffice;
    private String series;
    private int price;
    @Column(name = "isbn")
    private String isbn;
    private int pages;
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;
}
