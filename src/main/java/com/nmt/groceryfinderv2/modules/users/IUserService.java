package com.nmt.groceryfinderv2.modules.users;

import com.nmt.groceryfinderv2.modules.users.dtos.AccountDto;
import com.nmt.groceryfinderv2.modules.users.dtos.CreateUserDto;
import com.nmt.groceryfinderv2.modules.users.dtos.UpdateUserDto;
import com.nmt.groceryfinderv2.modules.users.dtos.UserDto;
import java.util.Optional;

public interface IUserService {
    UserDto createOne(CreateUserDto data);
    UserDto updateOne(String email, UpdateUserDto data);
    Optional<AccountDto> getAccountUserByEmail(String email);
}
