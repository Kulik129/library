package com.example.services;

import com.example.DTO.BookResponse;
import com.example.DTO.PersonResponse;
import com.example.DTO.Request;
import com.example.DTO.Response;
import com.example.models.Issue;
import com.example.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class IssueService implements CrudService<Response, Request> {
    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;
    private final BookProvider provider;

    @Override
    public List<Response> getAll() {
        return issueRepository.findAll().stream()
                .map(it -> modelMapper.map(it, Response.class))
                .collect(Collectors.toList());
    }

    @Override
    public Response getByUuid(UUID uuid) {
        Optional<Issue> issue = issueRepository.findByUuid(uuid);
        return modelMapper.map(issue, Response.class);
    }

    @Transactional
    @Override
    public Response create(Request request) {
        checkIssueBook(request.getBookUuid());
        findByBookByUuid(request.getBookUuid());

        getPerson(request.getReaderUuid());

        provider.addBookPerson(request);
        Issue createIssue = createIssue(request);
        return modelMapper.map(createIssue, Response.class);
    }

    private PersonResponse getPerson(UUID uuid) {
        PersonResponse person = provider.getPersonByUuid(uuid);
        if (person == null) {
            throw new RuntimeException("Пользователь не найден");
        }
        return person;
    }

    private String checkIssueBook(String uuid) {
        issueRepository.findAll().stream()
                .filter(it -> it.getBookId().equals(uuid))
                .findFirst()
                .ifPresent(ex -> {
                    throw new RuntimeException("Книга уже выдана");
                });
        return uuid;
    }

    private BookResponse findByBookByUuid(String id) {
        BookResponse bookResponse = provider.getBookById(id);
        if (bookResponse == null) {
            throw new RuntimeException("Книга не найдена");
        }
        return bookResponse;
    }

    private Issue createIssue(Request request) {
        Issue issue = Issue.builder()
                .uuid(UUID.randomUUID())
                .issuedAt(LocalDate.now())
                .bookId(request.getBookUuid())
                .readerId(request.getReaderUuid())
                .build();
        return issueRepository.save(issue);
    }

    @Override
    public Response update(Long id, Request request) {
        return issueRepository.findById(id)
                .map(issue -> {
                    issue.setBookId(request.getBookUuid());
                    issue.setReaderId(request.getReaderUuid());
                    Issue save = issueRepository.save(issue);
                    return modelMapper.map(save, Response.class);
                })
                .orElseThrow(() -> new RuntimeException("Выдача не найдена"));
    }

    @Override
    public void deleteById(Long id) {
        issueRepository.deleteById(id);
    }
}
