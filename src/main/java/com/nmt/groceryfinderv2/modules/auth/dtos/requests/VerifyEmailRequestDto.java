package com.nmt.groceryfinderv2.modules.auth.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record VerifyEmailRequestDto (
        @NotEmpty(message = "Email is required")
        @Email
        String email
){
}
