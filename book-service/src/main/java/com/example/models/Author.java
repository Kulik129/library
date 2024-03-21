package com.example.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String name;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    private String preview;
    private double rating;
    @OneToMany(mappedBy = "author")
    private List<Book> books;
}
