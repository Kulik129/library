package com.example.services;

import com.example.DTO.Request;
import com.example.DTO.Response;
import com.example.models.Author;
import com.example.models.Book;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class BookService implements CrudService<Response, Request> {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Response> getAll() {
        return bookRepository.findAll().stream()
                .map(it -> modelMapper.map(it, Response.class)).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    @Override
    public Response getByUuid(UUID uuid) {
        Optional<Book> book = bookRepository.findByUuid(uuid);
        return modelMapper.map(book, Response.class);
    }
    @Transactional
    @Override
    public Response create(Request request) {
        Author author = Author.builder()
                .uuid(UUID.randomUUID())
                .name(request.getAuthor().getName())
                .dateOfBirth(request.getAuthor().getDateOfBirth())
                .preview(request.getAuthor().getPreview())
                .rating(request.getAuthor().getRating())
                .build();

        Author saveAuthor = authorRepository.save(author);

        Book book = Book.builder()
                .uuid(UUID.randomUUID())
                .title(request.getTitle())
                .description(request.getDescription())
                .releaseDate(request.getReleaseDate())
                .publishingOffice(request.getPublishingOffice())
                .series(request.getSeries())
                .price(request.getPrice())
                .isbn(request.getIsbn())
                .pages(request.getPages())
                .author(saveAuthor)
                .build();

        Book savedBook = bookRepository.save(book);

        return modelMapper.map(savedBook, Response.class);
    }
    @Transactional
    @Override
    public Response update(Long id, Request request) {
        return bookRepository.findById(id).map(book -> {

                    book.setTitle(request.getTitle());
                    book.setDescription(request.getDescription());
                    book.setReleaseDate(request.getReleaseDate());
                    book.setPublishingOffice(request.getPublishingOffice());
                    book.setSeries(request.getSeries());
                    book.setPrice(request.getPrice());
                    book.setIsbn(request.getIsbn());
                    book.setPages(request.getPages());
                    Book save = bookRepository.save(book);
                    authorRepository.findById(save.getAuthor().getId())
                            .map(author -> {
                                author.setName(request.getAuthor().getName());
                                author.setDateOfBirth(request.getAuthor().getDateOfBirth());
                                author.setPreview(request.getAuthor().getPreview());
                                author.setRating(request.getAuthor().getRating());
                                return authorRepository.save(author);
                            });
                    return modelMapper.map(save, Response.class);
                })
                .orElseThrow(() -> new RuntimeException("Книга не найдена"));
    }

    @Transactional
    @Override
    public List<Response> deleteById(Long id) {
        bookRepository.deleteById(id);
        return bookRepository.findAll().stream()
                .map(it -> modelMapper.map(it, Response.class))
                .collect(Collectors.toList());
    }
}
