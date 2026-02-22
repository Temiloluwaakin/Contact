package com.example.contact.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@Slf4j
@RequiredArgsConstructor
public class ApiClient {

    private final RestClient restClient;

    public <T> T get(String url,
                     ParameterizedTypeReference<T> responseType,
                     Object... uriVars) {

        log.info("GET request to: {}", url);

        try {
            return restClient.get()
                    .uri(url, uriVars)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, (request, response) -> {
                        throw new RuntimeException("GET failed for URL: " + url);
                    })
                    .body(responseType);

        } catch (Exception e) {
            log.error("Unexpected error during GET to {}: {}", url, e.getMessage());
            throw new RuntimeException("Unexpected error during GET");
        }
    }

    public <T, R> R post(String url, T requestBody, Class<R> responseType) {
        log.info("POST request to: {}", url);
        try {
            return restClient.post()
                    .uri(url)
                    .body(requestBody)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, (request, response) -> {
                        throw new RuntimeException("POST failed for URL: "  + url);
                    })
                    .body(responseType);
        }
        catch (Exception e) {
            log.error("Unexpected error during POST to {}: {}", url, e.getMessage());
            throw new RuntimeException("Unexpected error during POST");

        }
    }
}
