package com.nmt.groceryfinderv2.modules.auth;

import com.nmt.groceryfinderv2.exceptions.ModuleException;
import com.nmt.groceryfinderv2.exceptions.messages.AuthExceptionMessages;
import com.nmt.groceryfinderv2.modules.auth.dtos.requests.LoginRequestDto;
import com.nmt.groceryfinderv2.modules.auth.dtos.requests.LogoutRequestDto;
import com.nmt.groceryfinderv2.modules.auth.dtos.requests.CreateAccountRequestDto;
import com.nmt.groceryfinderv2.modules.auth.dtos.responses.AuthenticationResponseDto;
import com.nmt.groceryfinderv2.modules.auth.dtos.responses.LoginResponseDto;
import com.nmt.groceryfinderv2.modules.auth.dtos.responses.RegisterAdminResponseDto;
import com.nmt.groceryfinderv2.shared.logging.LoggingInterceptor;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author LENOVO
 * @project ecommerce-spring-boot-be
 * @date 8/13/2024
 */
@RestController
@RequestMapping("/v1/auth")
@Tag(name = "AUTH")
public class AuthController {
    private final IAuthService authService;

    @Autowired
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @LoggingInterceptor
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto data)  {
        try{
            LoginResponseDto response = authService.login(data);
            return ResponseEntity.ok(response);
        }catch (AuthException e){
            if(e.getMessage().equals(AuthExceptionMessages.PASSWORD_WRONG.getMessage())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new AuthenticationResponseDto(false, e.getMessage()));
            }
            else if(e.getMessage().equals(AuthExceptionMessages.LOGIN_INVALID.getMessage())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new AuthenticationResponseDto(false, e.getMessage()));
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthenticationResponseDto(
                            false,
                            AuthExceptionMessages.LOGIN_FAILED.getMessage()
                    ));
        }
    }

    @LoggingInterceptor
    @PostMapping("/change-password")
    public ResponseEntity<AuthenticationResponseDto> changePassword(@RequestParam String email) {
        AuthenticationResponseDto response = authService.changePassword(email);
        return ResponseEntity.ok(response);
    }

    @LoggingInterceptor
    @PostMapping("/forget-password")
    public ResponseEntity<AuthenticationResponseDto> forgetPassword(@RequestParam String email) {
        AuthenticationResponseDto response = authService.forgetPassword(email);
        return ResponseEntity.ok(response);
    }

    @LoggingInterceptor
    @PostMapping("/logout")
    public ResponseEntity<AuthenticationResponseDto> logout(@Valid @RequestBody LogoutRequestDto data) throws AuthException {
        AuthenticationResponseDto response = authService.logout(data.email());
        return ResponseEntity.ok(response);
    }
}
