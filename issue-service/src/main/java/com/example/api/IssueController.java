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
        System.out.println(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @Operation(summary = "Получить книгу по ID",
            description = "Объект книга из сервиса google-books-service")
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookByUuid(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(provider.getBookById(id));
    }
}
