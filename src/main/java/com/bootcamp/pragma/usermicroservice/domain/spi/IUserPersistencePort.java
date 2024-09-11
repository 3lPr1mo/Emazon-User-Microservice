package com.bootcamp.pragma.usermicroservice.domain.spi;

import com.bootcamp.pragma.usermicroservice.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {
    void save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
}
