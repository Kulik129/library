package com.example.api;

import com.example.DTO.Request;
import com.example.DTO.Response;
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
@RequestMapping("/api/v1/book")
public class BookController {
    private final CrudService<Response, Request> service;
    @Operation(
            summary = "Получить список всех книг",
            description = "Возвращает список книг с авторами")
    @GetMapping()
    public ResponseEntity<List<Response>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }
    @Operation(
            summary = "Найти книгу по UUID",
            description = "Принимает UUID книги")
    @GetMapping("/{uuid}")
    public ResponseEntity<Response> getBuUuid(@PathVariable UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getByUuid(uuid));
    }
    @Operation(
            summary = "Удалить книгу",
            description = "Принимает ID книги")
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Response>> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }
    @Operation(
            summary = "Добавить книгу и автора",
            description = "Принимает информацию о книге и об авторе.")
    @PostMapping()
    public ResponseEntity<Response> create(@RequestBody Request request) {
        Response response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @Operation(
            summary = "Обновить информацию о книге и авторе",
            description = "Принимает ID книги")
    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable Long id, @RequestBody Request request) {
        Response update = service.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }
}
