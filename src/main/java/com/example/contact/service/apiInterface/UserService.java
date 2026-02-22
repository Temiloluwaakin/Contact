package com.example.contact.service.apiInterface;

import com.example.contact.model.*;
import java.util.List;

public interface UserService {
    ApiResponse<UserResponse> register(UserRequest request);
    ApiResponse<UserResponse> login(LoginRequest req);
    ApiResponse<List<UsersDto.AllUsersResp>> getAllUsers();
}
