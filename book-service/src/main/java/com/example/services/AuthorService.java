package com.example.services;

import com.example.DTO.AuthorRequest;
import com.example.DTO.AuthorResponse;
import com.example.models.Author;
import com.example.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService implements CrudService<AuthorResponse, AuthorRequest> {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AuthorResponse> getAll() {
        return authorRepository.findAll().stream()
                .map(it -> modelMapper.map(it, AuthorResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public AuthorResponse getByUuid(UUID uuid) {
        return modelMapper.map(authorRepository.findByUuid(uuid), AuthorResponse.class);
    }

    @Override
    public AuthorResponse create(AuthorRequest request) {
        Author author = Author.builder()
                .uuid(UUID.randomUUID())
                .name(request.getName())
                .dateOfBirth(request.getDateOfBirth())
                .preview(request.getPreview())
                .rating(request.getRating())
                .build();
        Author savedAuthor = authorRepository.save(author);
        return modelMapper.map(savedAuthor, AuthorResponse.class);
    }

    @Override
    public AuthorResponse update(Long id, AuthorRequest request) {
        return authorRepository.findById(id).map(author -> {
                    author.setName(request.getName());
                    author.setDateOfBirth(request.getDateOfBirth());
                    author.setPreview(request.getPreview());
                    author.setRating(request.getRating());
                    Author savedAuthor = authorRepository.save(author);
                    return modelMapper.map(savedAuthor, AuthorResponse.class);
                })
                .orElseThrow(() -> new RuntimeException("Автор не найден"));
    }

    @Override
    public List<AuthorResponse> deleteById(Long id) {
        authorRepository.deleteById(id);
        return authorRepository.findAll().stream()
                .map(it -> modelMapper.map(it, AuthorResponse.class))
                .collect(Collectors.toList());
    }
}
