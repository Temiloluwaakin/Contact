package com.example.contact.controller.v1;

import com.example.contact.model.ApiResponse;
import com.example.contact.model.LoginRequest;
import com.example.contact.model.UserRequest;
import com.example.contact.model.UserResponse;
import com.example.contact.service.apiInterface.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "Operations related to user management")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Register a new user",
            description = """
        Registers a new user.

        ### Request Fields

        | Field  | Type   | Required | Description |
        |--------|--------|----------|-------------|
        | email  | string | Yes      | User email address |
        | name   | string | Yes      | Full name of the user |
        | age    | number | Yes      | Age of the user |
        | phone  | string | Yes      | Phone number |
        """
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User successfully registered")
    @PostMapping("/register")
    public ApiResponse<UserResponse> register(@RequestBody UserRequest request){
        try{
            var response = userService.register(request);
            return response;
        }
        catch (Exception ex){
            return null;
        }
    }


    @PostMapping("/login")
    public  ApiResponse<UserResponse> login(@RequestBody LoginRequest req){
        try{
            var res = userService.login(req);
            return res;
        }
        catch (Exception ex){
            log.warn("an exception occurerd {}", ex.getMessage());
            return null;
        }
    }
}
