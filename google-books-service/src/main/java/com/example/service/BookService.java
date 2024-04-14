package com.example.service;

import com.example.dto.BookResponse;
import com.example.entity.BookEntity;
import com.example.entity.IndustryIdentifier;
import com.example.repository.BookRepository;
import com.example.repository.IndustryIdentifierRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BookService {
    private final GoogleService service;
    private final BookRepository bookRepository;
    private final IndustryIdentifierRepository identifierRepository;
    private final ModelMapper modelMapper;

    public List<BookResponse> search(String title) {
        return Optional.ofNullable(findBookByTitle(title))
                .map(books -> books.isEmpty() ? Stream.of(findBookInGoogleBooks(title)) : books.stream())
                .orElse(Stream.empty())
                .collect(Collectors.toList());
    }

    private BookResponse findBookInGoogleBooks(String search) {
        BookResponse response = service.getBookResponse(search);
        BookEntity bookEntity = modelMapper.map(response, BookEntity.class);
        BookEntity savedBook = bookRepository.save(bookEntity);

        if (response.getIndustryIdentifiers() != null && !response.getIndustryIdentifiers().isEmpty()) {
            IndustryIdentifier identifier = IndustryIdentifier.builder()
                    .identifier(response.getIndustryIdentifiers().get(0).getIdentifier())
                    .type(response.getIndustryIdentifiers().get(0).getType())
                    .book(savedBook)
                    .build();
            identifierRepository.save(identifier);
        }
        return modelMapper.map(savedBook, BookResponse.class);
    }

    private List<BookResponse> findBookByTitle(String title) {
        return bookRepository.findByBookTitle(title)
                .stream()
                .map(it -> {
                    BookResponse map = modelMapper.map(it, BookResponse.class);
                    return map;
                })
                .collect(Collectors.toList());
    }

    public BookResponse getBookById(String id) {
        return Optional.of(bookRepository.findById(id))
                .map(bookEntity -> {
                    BookResponse bookResponse = modelMapper.map(bookEntity, BookResponse.class);
                    return bookResponse;
                })
                .orElseThrow(() -> new RuntimeException("Книга не найдена"));
    }

    public Page<BookResponse> getAll(Pageable pageable) {
        Page<BookEntity> bookEntities = bookRepository.findAll(pageable);
        List<BookResponse> collect = bookEntities.getContent().stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .collect(Collectors.toList());

        return new PageImpl<>(collect, pageable, bookEntities.getTotalElements());
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteByBookId(id);
    }

    public BookResponse updateBookDescription(String id, String updateDes) {
        BookEntity book = bookRepository.findById(id);
        book.setDescription(updateDes);
        BookEntity save = bookRepository.save(book);
        return modelMapper.map(save, BookResponse.class);
    }
}
