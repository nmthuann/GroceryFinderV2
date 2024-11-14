package com.nmt.groceryfinderv2.modules.auth.dtos.responses;

public record LoginResponseDto(
        String email,
        String name,
        String accessToken,
        String refreshToken
) {
}
