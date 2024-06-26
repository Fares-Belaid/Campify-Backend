package com.app.compify.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequest {

    @NotNull(message = "email is required")
    @NotEmpty(message = "email is required")
    private String email;
    @NotNull(message = "password is required")
    @NotEmpty(message = "password is required")
    private String password;
}