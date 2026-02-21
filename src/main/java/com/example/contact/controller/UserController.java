package com.example.contact.controller;

import com.example.contact.model.ApiResponse;
import com.example.contact.model.UserRequest;
import com.example.contact.model.UserResponse;
import com.example.contact.service.apiInterface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ApiResponse<UserResponse> register(@RequestBody UserRequest request){
        try{
            var response = userService.register(request);
            return  response;
        }
        catch (Exception ex){
            return null;
        }
    }
}
