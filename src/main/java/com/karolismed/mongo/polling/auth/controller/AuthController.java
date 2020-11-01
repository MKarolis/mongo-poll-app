package com.karolismed.mongo.polling.auth.controller;

import com.karolismed.mongo.polling.auth.dto.LoginRequestDto;
import com.karolismed.mongo.polling.auth.dto.LoginResponseDto;
import com.karolismed.mongo.polling.auth.dto.RegisterRequestDto;
import com.karolismed.mongo.polling.auth.service.AuthService;
import com.karolismed.mongo.polling.auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userDetailsService;
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        userDetailsService.registerUser(registerRequestDto);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return authService.authenticate(loginRequestDto);
    }
}
