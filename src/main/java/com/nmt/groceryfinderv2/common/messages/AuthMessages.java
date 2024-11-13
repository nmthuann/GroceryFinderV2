package com.nmt.groceryfinderv2.common.messages;

import lombok.Getter;

/**
 * @author LENOVO
 * @project ecommerce-spring-boot-be
 * @date 8/16/2024
 */
@Getter
public enum AuthMessages {
    RESET_PASSWORD_SUCCESS("Reset Password Thành Công!"),
    VERIFY_EMAIL_SUCCESS("Verify email success."),
    OTP_VALID("OTP verified successful."),
    RESEND_OTP_SUCCESS("OTP resend successfully.")
    ;
     private final String message;

    AuthMessages(String message){
         this.message = message;
     }
}
