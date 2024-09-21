package com.bootcamp.pragma.usermicroservice.domain.spi;

import com.bootcamp.pragma.usermicroservice.domain.model.User;

public interface IAuthPersistencePort {
    String encodePassword(String password);
    boolean matchPassword(String password, String encryptedPassword);
    String loginUser(User user);
}
