package com.bootcamp.pragma.usermicroservice.domain.api;

import com.bootcamp.pragma.usermicroservice.domain.model.User;

public interface IUserServicePort {
    void createUser(User user);
    User findUserByEmail(String email);
}
