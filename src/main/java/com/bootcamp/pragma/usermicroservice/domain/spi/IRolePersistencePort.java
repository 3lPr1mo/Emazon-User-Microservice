package com.bootcamp.pragma.usermicroservice.domain.spi;

import com.bootcamp.pragma.usermicroservice.domain.model.Role;

import java.util.Optional;

public interface IRolePersistencePort {
    Optional<Role> findByName(String name);
}
