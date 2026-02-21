package com.example.contact.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserResponse{
    private String name;
    private int age;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
}
