package com.nmt.groceryfinderv2.modules.users.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record AccountDto (
        String id,
        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        @Size(max = 50, message = "Email should not be longer than 50 characters")
        String email,
        String name,
        @NotNull(message = "Status is required")
        Boolean status,
        @NotBlank(message = "password is required")
        String password,

        @Size(max = 255, message = "Refresh token should not be longer than 255 characters")
        String refreshToken,
         String role
) {}