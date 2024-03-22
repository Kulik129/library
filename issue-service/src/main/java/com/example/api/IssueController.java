package com.example.api;

import com.example.DTO.BookResponse;
import com.example.DTO.Request;
import com.example.DTO.Response;
import com.example.services.BookProvider;
import com.example.services.CrudService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/issue")
public class IssueController {
    private final CrudService<Response, Request> service;
    private final BookProvider provider;

    @Operation(
            summary = "Получить список все выдач",
            description = "Список всех выданных книг")
    @GetMapping()
    public ResponseEntity<List<Response>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @Operation(
            summary = "Назначить книгу для выдачи",
            description = "Список всех выданных книг")
    @PostMapping()
    public ResponseEntity<Response> create(@RequestBody Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @Operation(summary = "Получить книгу по UUID",
    description = "Объект книга из сервиса book-service")
    @GetMapping("/{uuid}")
    public ResponseEntity<BookResponse> getBookByUuid(@PathVariable UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(provider.getBookByUuid(uuid));
    }

    @Operation(summary = "Получить все книги",
            description = "Лист книг из сервиса book-service")
    @GetMapping("/books")
    public ResponseEntity<List<BookResponse>> getAllBook() {
        return ResponseEntity.status(HttpStatus.OK).body(provider.getAllBook());
    }
}
