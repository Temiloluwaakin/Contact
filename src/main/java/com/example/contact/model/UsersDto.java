package com.example.contact.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

public class UsersDto {


    @Data
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AllUsersResp {

        private Long id;
        private String name;
        private String username;
        private String email;
        private Address address;
        private String phone;
        private String website;
        private Company company;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Address {
            private String street;
            private String suite;
            private String city;
            private String zipcode;
            private Geo geo;
        }

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Geo {
            private String lat;
            private String lng;
        }

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Company {
            private String name;
            private String catchPhrase;
            private String bs;
        }
    }
}
