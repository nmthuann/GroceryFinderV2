package com.nmt.groceryfinderv2.common.enums;

import lombok.Getter;

@Getter
public enum ApiVersionEnum {
    API_VERSION_V1("2024-11-15"),
    API_VERSION_V2("2025-01-15"),
    ;
    private final String timestamp;
    ApiVersionEnum(String timestamp){
        this.timestamp = timestamp;
    }
}
