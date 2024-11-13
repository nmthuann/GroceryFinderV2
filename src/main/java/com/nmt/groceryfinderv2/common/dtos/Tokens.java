package com.nmt.groceryfinderv2.common.dtos;

public record Tokens (
        String accessToken,
        String refreshToken
) {}