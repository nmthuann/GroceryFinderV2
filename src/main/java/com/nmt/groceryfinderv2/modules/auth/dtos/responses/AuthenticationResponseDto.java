package com.nmt.groceryfinderv2.modules.auth.dtos.responses;

public record AuthenticationResponseDto(
         Boolean success,
         String message
) {
}
