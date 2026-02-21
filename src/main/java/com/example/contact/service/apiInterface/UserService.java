package com.example.contact.service.apiInterface;

import com.example.contact.model.ApiResponse;
import com.example.contact.model.UserRequest;
import com.example.contact.model.UserResponse;

public interface UserService {
    ApiResponse<UserResponse> register(UserRequest request);
}
