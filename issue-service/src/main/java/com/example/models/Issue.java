package com.example.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "issue")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    @Column(name = "issued_at")
    private LocalDate issuedAt;
    @Column(name = "book_id")
    private String bookId;
    @Column(name = "reader_id")
    private UUID readerId;
}
