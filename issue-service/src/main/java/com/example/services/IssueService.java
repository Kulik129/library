package com.example.services;

import com.example.DTO.BookResponse;
import com.example.DTO.Request;
import com.example.DTO.Response;
import com.example.models.Issue;
import com.example.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public Response create(Request request) {
        UUID uuid = provider.getBookByUuid(request.getUuid()).getUuid();
        Issue issue = Issue.builder()
                .uuid(UUID.randomUUID())
                .issuedAt(LocalDate.now())
                .bookId(uuid)
                .build();
        issueRepository.save(issue);
        return modelMapper.map(issue, Response.class);
    }

    @Override
    public Response update(Long id, Request request) {
        return null;
    }

    @Override
    public List<Response> deleteById(Long id) {
        return null;
    }
}
