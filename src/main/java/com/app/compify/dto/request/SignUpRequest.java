package com.app.compify.dto.request;

import com.app.compify.dao.entity.Role;
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
public class SignUpRequest {

    @NotNull(message = "FirstName is required")
    @NotEmpty(message = "FirstName is required")
    private String firstName;
    @NotNull(message = "LastName is required")
    @NotEmpty(message = "LastName is required")
    private String lastName;
    @NotNull(message = "email is required")
    @NotEmpty(message = "email is required")
    private String email;
    @NotNull(message = "password is required")
    @NotEmpty(message = "password is required")
    private String password;
    @NotNull(message = "role is required")
    @NotEmpty(message = "role is required")
    private Role role;
}
