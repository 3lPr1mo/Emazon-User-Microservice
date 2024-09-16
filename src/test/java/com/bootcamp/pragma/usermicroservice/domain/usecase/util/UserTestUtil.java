package com.bootcamp.pragma.usermicroservice.domain.usecase.util;

import com.bootcamp.pragma.usermicroservice.domain.model.User;
import com.bootcamp.pragma.usermicroservice.domain.model.UserBuilder;

import java.util.Date;

public class UserTestUtil {
    private UserTestUtil() {}

    public static User generateUser() {
        UserBuilder userBuilder = new UserBuilder();
        return userBuilder.setId(1L)
                .setFirstname("Erick")
                .setLastname("Smith")
                .setEmail("erick.smith@gmail.com")
                .setPhone("12312321")
                .setDateBirth(new Date())
                .setPassword("password")
                .setRole(null)
                .build();
    }
}
