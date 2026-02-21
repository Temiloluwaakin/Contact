package com.example.contact.service;

import com.example.contact.config.AppProperties;
import com.example.contact.model.ApiResponse;
import com.example.contact.model.ApiResponseCodes;
import com.example.contact.model.UserRequest;
import com.example.contact.model.UserResponse;
import com.example.contact.service.apiInterface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AppProperties appProperties;


    @Override
    public ApiResponse<UserResponse> register(UserRequest request) {

        if (request.getEmail() == null || request.getName() == null){
            return new ApiResponse<>(
                    ApiResponseCodes.ResponseCode.Failed.getCode(),
                    ApiResponseCodes.ResponseCode.Failed.getMessage(),
                    null
            );
        }
        if (request.getAge() < appProperties.getMinAge()) {
            return new ApiResponse<>(
                    ApiResponseCodes.ResponseCode.Failed.getCode(),
                    ApiResponseCodes.ResponseCode.Failed.getMessage(),
                    null
            );
        }

        LocalDate dob = LocalDate.now().minusYears(request.getAge());

        UserResponse response = UserResponse.builder()
                .name(request.getName())
                .age(request.getAge())
                .email(request.getEmail())
                .phone(request.getPhone())
                .dateOfBirth(dob)
                .build();

        return new ApiResponse<>(
                "OO",
                "User Registered",
                response
        );
    }
}
