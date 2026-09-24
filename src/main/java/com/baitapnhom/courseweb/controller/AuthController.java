package com.baitapnhom.courseweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baitapnhom.courseweb.dto.request.AuthenticationRequest;
import com.baitapnhom.courseweb.dto.request.RegisterRequest;
import com.baitapnhom.courseweb.dto.response.ApiResponse;
import com.baitapnhom.courseweb.dto.response.AuthenticationResponse;
import com.baitapnhom.courseweb.entity.User;
import com.baitapnhom.courseweb.service.AuthenticationService;
import com.baitapnhom.courseweb.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ApiResponse<User> register(@RequestBody @Valid RegisterRequest request) {
        User result = userService.register(request);
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        return apiResponse;
    }

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        boolean result = authenticationService.authenticate(request);

        // Bọc kết quả true/false vào Object
        AuthenticationResponse authResponse = new AuthenticationResponse();
        authResponse.setAuthenticated(result);

        // Trả về ApiResponse chứa Object đó
        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(authResponse);

        return apiResponse;
    }
}
