package com.nmt.groceryfinderv2.modules.users;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/13/2024
 */
@Getter
@Setter
@Document(collection = "users")
public class UserDocument {
    @Id
    private String id;

    @Field("email")
    @Indexed(unique = true)
    private String email;

    @Field("name")
    private String name;

    @Field("phone")
    @Indexed(unique = true)
    private String phone;

    @Field("password")
    private String password;

    @Field("status")
    private Boolean status = true;

    @Field("refresh_token")
    private String refreshToken = null;

    @Field("role")
    private String role;
}
