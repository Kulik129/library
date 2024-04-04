package com.example.repository;

import com.example.models.Book;
import com.example.models.IndustryIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndustryIdentifierRepository extends JpaRepository<IndustryIdentifier, Long> {
}
