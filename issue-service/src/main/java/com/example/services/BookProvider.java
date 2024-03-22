package com.example.services;

import com.example.DTO.BookResponse;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class BookProvider {
    private final WebClient webClient;
    private final String BASE_URL_BOOK_SERVICE = "http://book-service/api/v1/book";

    public BookProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
        webClient = WebClient.builder()
                .filter(loadBalancerExchangeFilterFunction)
                .build();
    }

    /**
     * Отправить Get запрос в book-service
     * Get http:localhost:8080/api/v1/book/uuid
     *
     * @return получить книгу по UUID
     */
    public BookResponse getBookByUuid(UUID uuid) {
        BookResponse response = webClient.get()
                .uri(BASE_URL_BOOK_SERVICE + "/" + uuid)
                .retrieve()
                .bodyToMono(BookResponse.class)
                .block();
        return response;
    }

    public List<BookResponse> getAllBook() {
        Flux<BookResponse> block = webClient.get()
                .uri(BASE_URL_BOOK_SERVICE)
                .retrieve()
                .bodyToFlux(BookResponse.class);
        return block.collectList().block();
    }

//    private String getBookServiceIP() {
//        Application application = eurekaClient.getApplication("BOOK-SERVICE");
//        List<InstanceInfo> instances = application.getInstances();
//
//        int randomIndex = ThreadLocalRandom.current().nextInt(instances.size());
//        InstanceInfo randomInstance = instances.get(randomIndex);
//
//        return randomInstance.getHomePageUrl();
//    }
}
