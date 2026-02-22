package com.example.contact.service.impl;

import com.example.contact.client.ApiClient;
import com.example.contact.config.AppProperties;
import com.example.contact.model.*;
import com.example.contact.service.apiInterface.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j//for logging
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AppProperties appProperties;
    private final ApiClient apiClient;

    @Override
    public ApiResponse<UserResponse> register(UserRequest request) {
        try{
            log.info("Registering user with email: {}", request.getEmail());

            if (request.getEmail().isBlank() || request.getName().isBlank())
            {
                log.warn("email or name is blank");
                return new ApiResponse<>(
                        ApiResponseCodes.ResponseCode.Failed.getCode(),
                        ApiResponseCodes.ResponseCode.Failed.getMessage(),
                        null
                );
            }
            if (request.getAge() < appProperties.getMinAge()) {
                log.warn("User {} is below minimum age: {}", request.getEmail(), request.getAge());
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

            log.info("successfully Registered the contact with detail {}", request.toString());
            return new ApiResponse<>(
                    ApiResponseCodes.ResponseCode.SUCCESS.getCode(),
                    ApiResponseCodes.ResponseCode.SUCCESS.getMessage(),
                    response
            );
        }
        catch (Exception ex){
            log.warn("an exception occured {}", ex.getMessage());
            return new ApiResponse<>(
                    ApiResponseCodes.ResponseCode.Exception.getCode(),
                    ApiResponseCodes.ResponseCode.Exception.getMessage(),
                    null
            );
        }
    }


    @Override
    public ApiResponse<UserResponse> login(LoginRequest req) {
        try{
            log.info("user with email: {} is login in", req.getEmail());

            if(req.getEmail().isBlank() || req.getPassword().isBlank()){
                log.info("the user entered blanc details");
                return new ApiResponse<>(
                        ApiResponseCodes.ResponseCode.Failed.getCode(),
                        ApiResponseCodes.ResponseCode.Failed.getMessage(),
                        null
                );
            }



            return new ApiResponse<>(
                    ApiResponseCodes.ResponseCode.SUCCESS.getCode(),
                    ApiResponseCodes.ResponseCode.SUCCESS.getMessage(),
                    null
            );
        }
        catch (Exception e) {
            log.warn("an exception occured {}", e.getMessage());
            return new ApiResponse<>(
                    ApiResponseCodes.ResponseCode.Exception.getCode(),
                    ApiResponseCodes.ResponseCode.Exception.getMessage(),
                    null
            );
        }
    }


    @Override
    public ApiResponse<List<UsersDto.AllUsersResp>> getAllUsers(){
        try{
            log.info("get all users - start");

            var url = appProperties.getClients().getUserService().getBaseUrl() + appProperties.getClients().getUserService().getEndpoints().get("get-user");

            log.info("Fetching all users calling: {}", url);
            List<UsersDto.AllUsersResp> users = apiClient.get(url, new ParameterizedTypeReference<List<UsersDto.AllUsersResp>>() {});
            if (users == null) {
                log.info("the users endpoint returned null");
                return new ApiResponse<>(
                        ApiResponseCodes.ResponseCode.Failed.getCode(),
                        ApiResponseCodes.ResponseCode.Failed.getMessage(),
                        null
                );
            }
            log.info("users endpoint returned: {}", users.toString());


            //get the individual
            var email = "Shanna@melissa.tv";
            List<UsersDto.AllUsersResp> user =
                    apiClient.get(
                            url+"?email={email}",
                            new ParameterizedTypeReference<List<UsersDto.AllUsersResp>>() {},
                            email
                    );

            return new ApiResponse<>(
                    ApiResponseCodes.ResponseCode.SUCCESS.getCode(),
                    ApiResponseCodes.ResponseCode.SUCCESS.getMessage(),
                    users
            );
        }
        catch (Exception e) {
            log.warn("an exception occured {}", e.getMessage());
            return new ApiResponse<>(
                    ApiResponseCodes.ResponseCode.Exception.getCode(),
                    ApiResponseCodes.ResponseCode.Exception.getMessage(),
                    null
            );
        }
    }
}