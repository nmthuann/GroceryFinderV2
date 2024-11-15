package com.nmt.groceryfinderv2.modules.auth;

import com.nmt.groceryfinderv2.exceptions.ModuleException;
import com.nmt.groceryfinderv2.modules.auth.dtos.requests.*;
import com.nmt.groceryfinderv2.modules.auth.dtos.responses.AuthenticationResponseDto;
import com.nmt.groceryfinderv2.modules.auth.dtos.responses.LoginResponseDto;
import com.nmt.groceryfinderv2.modules.auth.dtos.responses.RegisterAdminResponseDto;
import jakarta.mail.MessagingException;
import jakarta.security.auth.message.AuthException;

public interface IAuthService {
    LoginResponseDto login (LoginRequestDto data) throws AuthException;
    AuthenticationResponseDto logout(String email) throws AuthException;

    AuthenticationResponseDto verifyEmail(VerifyEmailRequestDto data);
    AuthenticationResponseDto changePassword(String email);
    AuthenticationResponseDto forgetPassword(String email);

}
