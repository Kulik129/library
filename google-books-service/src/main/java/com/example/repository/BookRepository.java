package com.example.repository;

import com.example.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    BookEntity findById(String id);
    @Query("SELECT b FROM BookEntity b WHERE b.title ILIKE %:title%")
    List<BookEntity> findByBookTitle(String title);

    @Transactional
    void deleteByBookId(Long id);
}
