package com.nmt.groceryfinderv2.modules.users.dtos;

import jakarta.validation.constraints.NotEmpty;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/14/2024
 */
public record UpdateUserDto(
        String refreshToken,
        @NotEmpty String name
) {
}
