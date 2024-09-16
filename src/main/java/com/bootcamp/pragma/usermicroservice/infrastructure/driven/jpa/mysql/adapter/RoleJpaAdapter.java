package com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.adapter;

import com.bootcamp.pragma.usermicroservice.domain.model.Role;
import com.bootcamp.pragma.usermicroservice.domain.spi.IRolePersistencePort;
import com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name).map(roleEntityMapper::toModelFromEntity);
    }
}
