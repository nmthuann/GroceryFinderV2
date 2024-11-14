package com.nmt.groceryfinderv2.modules.auth.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

/**
 * @author LENOVO
 * @project ecommerce-spring-boot-be
 * @date 9/5/2024
 */
public record CreateAccountRequestDto(
        @NotEmpty(message = "Email is required")
        @Email
        String email,
        @NotEmpty String phone,
        @NotEmpty String name,
        Date birthday,
        Integer authMethodId,
        Integer roleId
) {
}
