package com.example.service;

import com.example.models.Book;
import com.example.dto.BookResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleService {
    private final static String API_KEY = "AIzaSyB-CJ8EaCvceftL3VAcHmUVkm2sKZCOAg8";
    public BookResponse getBookResponse(String search) {
        Book book = searchBook(search);

        BookResponse response = BookResponse.builder()
                .id(book.getItems().get(0).getId())
                .title(book.getItems().get(0).getVolumeInfo().getTitle())
                .authors(book.getItems().get(0).getVolumeInfo().getAuthors())
                .publishedDate(book.getItems().get(0).getVolumeInfo().getPublishedDate())
                .description(book.getItems().get(0).getVolumeInfo().getDescription())
                .imageLinks(book.getItems().get(0).getVolumeInfo().getImageLinks().getThumbnail())
                .buyLink(book.getItems().get(0).getSaleInfo().getBuyLink())
                .listPrice(book.getItems().get(0).getSaleInfo().getListPrice()) //
                .previewLink(book.getItems().get(0).getVolumeInfo().getPreviewLink())
                .industryIdentifiers(book.getItems().get(0).getVolumeInfo().getIndustryIdentifiers()) //
                .pageCount(book.getItems().get(0).getVolumeInfo().getPageCount())
                .build();
        return response;
    }

    private static Book searchBook(String value) {
        String url = "https://www.googleapis.com/books/v1/volumes?q="+value+"&key=" + API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        Book book = restTemplate.getForObject(url, Book.class);
        return book;
    }
}
