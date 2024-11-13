package com.nmt.groceryfinderv2.common.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {

    ADMIN(1),
    USER(2),
    CUSTOMER(5),
    MANAGER(3);

    private final int roleId;

    RoleEnum(int roleId){
        this.roleId = roleId;
    }

}
