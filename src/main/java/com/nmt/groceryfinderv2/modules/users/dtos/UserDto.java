package com.nmt.groceryfinderv2.modules.users.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserDto(
        String id,

        @Email(message = "Email should be valid")
        @Size(max = 50, message = "Email should not be longer than 50 characters")
        String email,

        @NotEmpty(message = "Status is required")
        Boolean status,

        @NotEmpty
        String name,

        @NotEmpty(message = "Phone number is required")
        @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
        String phone,

        String refreshToken,

        @NotEmpty(message = "Role is required")
        String role
) {
}