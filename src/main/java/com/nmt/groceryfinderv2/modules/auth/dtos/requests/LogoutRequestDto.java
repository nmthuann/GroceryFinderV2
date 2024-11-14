package com.nmt.groceryfinderv2.modules.auth.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/15/2024
 */
public record LogoutRequestDto (
        @NotEmpty(message = "Email is required")
        @Email
        String email
) {}
