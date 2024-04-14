package com.example.entity;

import com.example.models.Book;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_table")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String id;
    private String title;
    @ElementCollection
    private List<String> authors;
    private String publishedDate;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String imageLinks;
    private String buyLink;
    @Embedded
    private Book.ListPrice listPrice;
    private String previewLink;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<IndustryIdentifier> industryIdentifiers;
    private int pageCount;
}

