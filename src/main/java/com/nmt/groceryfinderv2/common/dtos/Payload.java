package com.nmt.groceryfinderv2.common.dtos;

import java.util.UUID;

public record Payload(UUID userId, String email, String role) {
}
