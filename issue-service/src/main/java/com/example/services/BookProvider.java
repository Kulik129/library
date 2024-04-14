package com.example.services;

import com.example.DTO.BookResponse;
import com.example.DTO.PersonResponse;
import com.example.DTO.Request;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
public class BookProvider {
    private final WebClient webClient;

    public BookProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
        webClient = WebClient.builder()
                .filter(loadBalancerExchangeFilterFunction)
                .build();
    }

    /**
     * Отправить Get запрос в google-books-service
     *
     * @return получить книгу по ID
     */
    public BookResponse getBookById(String id) {
        BookResponse response = webClient.get()
                .uri("http://google-books-service/api/v1/google-books/" + id)
                .retrieve()
                .bodyToMono(BookResponse.class)
                .block();
        return response;
    }

    public PersonResponse getPersonByUuid(UUID uuid) {
        PersonResponse response = webClient.get()
                .uri("http://person-service/api/v1/person/" + uuid)
                .retrieve()
                .bodyToMono(PersonResponse.class)
                .block();
        return response;
    }

    public PersonResponse addBookPerson(Request request) {
        PersonResponse response = webClient.post()
                .uri("http://person-service/api/v1/person/add-book")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(PersonResponse.class)
                .block();
        return response;
    }

//
//    public List<BookResponse> getAllBook() {
//        Flux<BookResponse> block = webClient.get()
//                .uri("http://google-books-service/api/v1/google-books/")
//                .retrieve()
//                .bodyToFlux(BookResponse.class);
//        return block.collectList().block();
//    }
}
