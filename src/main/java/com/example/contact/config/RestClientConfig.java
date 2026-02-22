package com.example.contact.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;


@Configuration
@RequiredArgsConstructor
public class RestClientConfig {

    private final AppProperties appProperties;

    @Bean
    public RestClient restClient() {

        SimpleClientHttpRequestFactory factory =
                new SimpleClientHttpRequestFactory();

        factory.setConnectTimeout(appProperties.getTimeout().getConnect());
        factory.setReadTimeout(appProperties.getTimeout().getRead());

        return RestClient.builder()
                .requestFactory(factory)
                .build();
    }
}
