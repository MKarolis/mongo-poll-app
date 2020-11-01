package com.karolismed.mongo.polling.auth.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
public class LoginRequestDto {
    @NotEmpty(message = "Username")
    @NotEmpty
    private String username;
    @NotEmpty(message = "Password is required")
    private String password;
}
