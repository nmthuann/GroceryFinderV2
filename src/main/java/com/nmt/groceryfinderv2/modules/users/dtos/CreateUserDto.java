package com.nmt.groceryfinderv2.modules.users.dtos;

import jakarta.validation.constraints.*;

/**
 * @author LENOVO
 * @project ecommerce-spring-boot-be
 * @date 8/16/2024
 */
public record CreateUserDto(
        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        @Size(max = 50, message = "Email should not be longer than 50 characters")
        String email,

        @NotBlank(message = "password is required")
        String hashedPassword,

        String refreshToken,

        @NotEmpty String name,

        @NotEmpty String phone,

        @NotNull Integer role
) {
}
