package com.example.contact.model;

import lombok.Builder;
import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private int age;
    private String email;
    private String phone;
}