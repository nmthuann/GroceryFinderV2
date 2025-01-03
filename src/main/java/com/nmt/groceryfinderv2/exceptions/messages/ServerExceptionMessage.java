package com.nmt.groceryfinderv2.exceptions.messages;

import lombok.Getter;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 1/3/2025
 */
@Getter
public enum ServerExceptionMessage {
    MISSING_REQUEST_PARAMETER("Missing the Request Param")
    ;


    private final String message;

    ServerExceptionMessage(String message){
        this.message = message;
    }
}
