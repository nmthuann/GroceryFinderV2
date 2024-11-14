package com.nmt.groceryfinderv2.modules.auth.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto (
        @NotBlank(message = "email cannot be empty or blank")
        String email,
        @NotBlank(message = "password cannot be empty or blank")
        String password
) {
}
