package com.nmt.groceryfinderv2.modules.auth;

import com.nmt.groceryfinderv2.common.dtos.Payload;
import com.nmt.groceryfinderv2.common.dtos.Tokens;
import com.nmt.groceryfinderv2.common.enums.RoleEnum;
import com.nmt.groceryfinderv2.common.enums.SubjectMailEnum;
import com.nmt.groceryfinderv2.exceptions.messages.AuthExceptionMessages;
import com.nmt.groceryfinderv2.modules.auth.dtos.requests.*;
import com.nmt.groceryfinderv2.modules.auth.dtos.responses.AuthenticationResponseDto;
import com.nmt.groceryfinderv2.modules.auth.dtos.responses.LoginResponseDto;
import com.nmt.groceryfinderv2.modules.auth.dtos.responses.RegisterAdminResponseDto;
import com.nmt.groceryfinderv2.modules.users.IUserService;
import com.nmt.groceryfinderv2.modules.users.dtos.AccountDto;
import com.nmt.groceryfinderv2.modules.users.dtos.CreateUserDto;
import com.nmt.groceryfinderv2.modules.users.dtos.UpdateUserDto;
import com.nmt.groceryfinderv2.modules.users.dtos.UserDto;
import com.nmt.groceryfinderv2.shared.redis.RedisService;
import com.nmt.groceryfinderv2.utils.JwtServiceUtil;
import com.nmt.groceryfinderv2.utils.MailServiceUtil;
import com.nmt.groceryfinderv2.utils.PasswordUtil;
import jakarta.mail.MessagingException;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    private static final String BASE_STRING_NUMERIC = "0123456789";
    private final JwtServiceUtil jwtServiceUtil;
    private final PasswordUtil passwordUtil;
    private final MailServiceUtil mailServiceUtil;
    private final IUserService userService;
    private final RedisService redisService;


    @Autowired
    public AuthService(
            IUserService userService,
            JwtServiceUtil jwtServiceUtil,
            PasswordUtil passwordUtil,
            MailServiceUtil mailServiceUtil,
            RedisService redisService
            ) {
        this.jwtServiceUtil = jwtServiceUtil;
        this.passwordUtil = passwordUtil;
        this.mailServiceUtil = mailServiceUtil;
        this.userService = userService;
        this.redisService = redisService;
    }

    private AccountDto getAccountByEmail(String email) throws AuthException {
        return this.userService.getAccountUserByEmail(email)
                .filter(AccountDto::status)
                .orElseThrow(() -> new AuthException(AuthExceptionMessages.LOGIN_INVALID.getMessage()));
    }

    @Override
    public LoginResponseDto login(LoginRequestDto data) throws AuthException {
        AccountDto account = getAccountByEmail(data.email());
        if (!this.passwordUtil.comparePassword(data.password(), account.password())) {
            throw new AuthException(AuthExceptionMessages.PASSWORD_WRONG.getMessage());
        }
        Tokens generateTokens = this.jwtServiceUtil.getTokens(new Payload(
                account.id(),
                account.email(),
                account.role()
        ));
        return new LoginResponseDto(
                data.email(),
                account.name(),
                generateTokens.accessToken(),
                generateTokens.refreshToken()
        );
    }

    @Override
    public AuthenticationResponseDto logout(String email) throws AuthException {
        AccountDto account = getAccountByEmail(email);
        UpdateUserDto updateUserDto = new UpdateUserDto(
                null, // No password change
                account.name()
        );
        this.userService.updateOne(email, updateUserDto);  // Update user (if necessary)
        return new AuthenticationResponseDto(
                true,
                "Logout Successfully"
        );
    }

    private RegisterAdminResponseDto createAccount (
            CreateAccountRequestDto data
    ) throws AuthException, MessagingException {
        if (this.userService.getAccountUserByEmail(data.email()).isPresent()) {
            throw new AuthException(AuthExceptionMessages.EMAIL_EXIST.getMessage());
        }

        String randomPassword = this.passwordUtil.randomPassword(8, BASE_STRING_NUMERIC);

        String htmlContent = this.mailServiceUtil.generateAdminRegistrationEmailContent(data.email(), randomPassword);

        String subject = SubjectMailEnum.REGISTER_ADMIN_SUBJECT.getSubject();
        this.mailServiceUtil.sendHtmlEmail(data.email(), subject, htmlContent);

        String hashedPassword = this.passwordUtil.hashPassword(randomPassword);

        CreateUserDto createUserDto = new CreateUserDto(
                data.email(),
                hashedPassword,
                "",
                data.name(),
                data.phone(),
                data.roleId()
        );
        UserDto userCreated = this.userService.createOne(createUserDto);
        Tokens tokens = this.jwtServiceUtil.getTokens(
                new Payload(
                        userCreated.id(),
                        data.email(),
                        RoleEnum.ADMIN.name()
                ));
        return new RegisterAdminResponseDto (
                userCreated.email(),
                userCreated.name(),
                tokens.accessToken(),
                tokens.refreshToken()
        );
    }

    @Override
    public AuthenticationResponseDto verifyEmail(VerifyEmailRequestDto data)  {
        return null;
    }

    @Override
    public AuthenticationResponseDto changePassword(String email) {
        return null;
    }

    @Override
    public AuthenticationResponseDto forgetPassword(String email) {
        return null;
    }
}