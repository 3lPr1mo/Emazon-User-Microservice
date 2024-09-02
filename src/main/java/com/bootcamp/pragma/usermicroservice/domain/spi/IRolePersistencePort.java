package com.bootcamp.pragma.usermicroservice.domain.spi;

import com.bootcamp.pragma.usermicroservice.domain.model.Role;

import java.util.Optional;

public interface IRolePersistencePort {
    void save(Role role);
    Optional<Role> findByName(String name);
}
