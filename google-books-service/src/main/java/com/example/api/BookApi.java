package com.example.api;

import com.example.dto.BookResponse;
import com.example.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/google-books")
public class BookApi {
    private final BookService service;

    @Operation(
            summary = "Найти книгу по названию",
            description = "Вернется найденная книга")
    @GetMapping("/title/{book}")
    ResponseEntity<List<BookResponse>> byTitle(@PathVariable String book) {
        return ResponseEntity.status(HttpStatus.OK).body(service.search(book));
    }

    @Operation(
            summary = "Получить список книг",
            description = "Вернется список книг. По дефолту 10")
    @GetMapping()
    ResponseEntity<Page<BookResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll(pageable));
    }

    @Operation(
            summary = "Получить книгу по id",
            description = "Вернется книга.")
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookByID(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getBookById(id));
    }
}
