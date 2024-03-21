package com.example.repository;

import com.example.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByUuid(UUID uuid);
    void deleteByUuid(UUID uuid);
}