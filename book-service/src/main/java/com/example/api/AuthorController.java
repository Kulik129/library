package com.example.api;

import com.example.DTO.AuthorRequest;
import com.example.DTO.AuthorResponse;
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
@RequestMapping("/api/v1/author")
public class AuthorController {
    private final CrudService<AuthorResponse, AuthorRequest> service;
    @Operation(
            summary = "Получить список всех авторов",
            description = "Возвращает список с авторами")
    @GetMapping()
    public ResponseEntity<List<AuthorResponse>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }
    @Operation(
            summary = "Найти автора по UUID",
            description = "Принимает UUID книги")
    @GetMapping("/{uuid}")
    public ResponseEntity<AuthorResponse> getBuUuid(@PathVariable UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getByUuid(uuid));
    }
    @Operation(
            summary = "Удалить автора",
            description = "Принимает ID книги")
    @DeleteMapping("/{id}")
    public ResponseEntity<List<AuthorResponse>> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }
    @Operation(
            summary = "Добавить автора",
            description = "Принимает информацию о авторе.")
    @PostMapping()
    public ResponseEntity<AuthorResponse> create(@RequestBody AuthorRequest request) {
        AuthorResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @Operation(
            summary = "Обновить информацию о авторе",
            description = "Принимает ID автора")
    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> update(@PathVariable Long id, @RequestBody AuthorRequest request) {
        AuthorResponse update = service.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }
}
