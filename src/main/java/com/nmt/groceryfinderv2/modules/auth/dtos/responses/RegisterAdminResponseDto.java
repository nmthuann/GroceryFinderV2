package com.nmt.groceryfinderv2.modules.auth.dtos.responses;

/**
 * @author LENOVO
 * @project ecommerce-spring-boot-be
 * @date 9/6/2024
 */
public record RegisterAdminResponseDto(
        String email,
        String name,
        String accessToken,
        String refreshToken
) {
}
