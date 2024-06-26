package com.example.api;

import com.example.dto.CreateIssueBookRequest;
import com.example.dto.Request;
import com.example.dto.Response;
import com.example.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @Operation(
            summary = "Найти пользователя",
            description = "Возвращает объект пользователя")
    @GetMapping("/{uuid}")
    public ResponseEntity<Response> getPerson(@PathVariable UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(personService.getPersonByUuid(uuid));
    }

    @Operation(
            summary = "Создать пользователя",
            description = "Возвращает объект пользователя")
    @PostMapping
    public ResponseEntity<Response> create(@RequestBody Request request) {
        return ResponseEntity.status(HttpStatus.OK).body(personService.create(request));
    }

    @Operation(
            summary = "Добавить книгу пользователю",
            description = "Возвращает объект пользователя")
    @PostMapping("/add-book")
    public ResponseEntity<Response> createIssue(@RequestBody CreateIssueBookRequest request) {
        personService.addIssueBook(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Получить всех пользователей",
            description = "Возвращает объект пользователей")
    @GetMapping
    public ResponseEntity<List<Response>> gelAll() {
        return ResponseEntity.status(HttpStatus.OK).body(personService.allPerson());
    }
}
