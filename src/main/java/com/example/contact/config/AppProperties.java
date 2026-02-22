package com.example.contact.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private int minAge;
    private String defaultCountry;
    private Clients clients;
    private Timeout timeout;

    //inside clients, we have this two
    @Data
    public static class Clients {
        private Client userService;
        private Client enrichmentService;
    }

    //inside the client we have this 2, endpoints is a list just in case there are multiple
    @Data
    public static class Client {
        private String baseUrl;
        private Map<String, String> endpoints;
    }

    @Data
    public static class Timeout {
        private int connect;
        private int read;
    }
}
