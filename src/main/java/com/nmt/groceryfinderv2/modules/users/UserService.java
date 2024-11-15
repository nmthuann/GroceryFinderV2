package com.nmt.groceryfinderv2.modules.users;

import com.nmt.groceryfinderv2.common.enums.RoleEnum;
import com.nmt.groceryfinderv2.modules.users.dtos.AccountDto;
import com.nmt.groceryfinderv2.modules.users.dtos.CreateUserDto;
import com.nmt.groceryfinderv2.modules.users.dtos.UpdateUserDto;
import com.nmt.groceryfinderv2.modules.users.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(
            IUserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }


    private RoleEnum getRoleById(Integer roleId) {
        return switch (roleId) {
            case 1 -> RoleEnum.ADMIN;
            case 2 -> RoleEnum.USER;
            default -> throw new IllegalArgumentException("Invalid roleId: " + roleId);
        };
    }

    @Override
    public UserDto createOne(CreateUserDto data) {
        UserDocument userDocument = new UserDocument();
        userDocument.setEmail(data.email());
        userDocument.setName(data.name());
        userDocument.setPhone(data.phone());
        userDocument.setPassword(data.hashedPassword());
        userDocument.setRefreshToken(data.refreshToken());
        userDocument.setStatus(true);
        userDocument.setRole(this.getRoleById(data.role()).name());
        UserDocument userCreated = this.userRepository.save(userDocument);
        return new UserDto(
                userCreated.getId(),
                userCreated.getEmail(),
                userCreated.getStatus(),
                userCreated.getName(),
                userCreated.getPhone(),
                userCreated.getRefreshToken(),
                userCreated.getRole()
        );
    }

    @Override
    public UserDto updateOne(String email, UpdateUserDto data) {
        return null;
    }

    @Override
    public Optional<AccountDto> getAccountUserByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .map(user -> new AccountDto(
                        user.getId(),
                        user.getEmail(),
                        user.getName(),
                        user.getStatus(),
                        user.getPassword(),
                        user.getRefreshToken(),
                        user.getRole()
                ));
    }
}
