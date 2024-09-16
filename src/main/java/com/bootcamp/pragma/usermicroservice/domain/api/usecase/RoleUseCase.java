package com.bootcamp.pragma.usermicroservice.domain.api.usecase;

import com.bootcamp.pragma.usermicroservice.domain.api.IRoleServicePort;
import com.bootcamp.pragma.usermicroservice.domain.exception.RoleNotFoundException;
import com.bootcamp.pragma.usermicroservice.domain.model.Role;
import com.bootcamp.pragma.usermicroservice.domain.spi.IRolePersistencePort;

public class RoleUseCase implements IRoleServicePort {

    private final IRolePersistencePort rolePersistencePort;

    public RoleUseCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public Role findRoleByName(String name) {
        return rolePersistencePort.findByName(name).orElseThrow(() -> new RoleNotFoundException(name));
    }
}
