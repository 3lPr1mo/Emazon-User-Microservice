package com.bootcamp.pragma.usermicroservice.domain.api.usecase;

import com.bootcamp.pragma.usermicroservice.domain.api.IAuthServicePort;
import com.bootcamp.pragma.usermicroservice.domain.api.IRoleServicePort;
import com.bootcamp.pragma.usermicroservice.domain.api.IUserServicePort;
import com.bootcamp.pragma.usermicroservice.domain.model.User;
import com.bootcamp.pragma.usermicroservice.domain.spi.IAuthPersistencePort;

public class AuthUseCase implements IAuthServicePort {

    private final IUserServicePort userServicePort;
    private final IAuthPersistencePort authPersistencePort;
    private final IRoleServicePort roleServicePort;

    public AuthUseCase(IUserServicePort userServicePort, IAuthPersistencePort authPersistencePort, IRoleServicePort roleServicePort) {
        this.userServicePort = userServicePort;
        this.authPersistencePort = authPersistencePort;
        this.roleServicePort = roleServicePort;
    }

    @Override
    public void registerAuxWarehouse(User user) {
        user.setRole(roleServicePort.findRoleByName("AUX_BODEGA"));
        register(user);
    }

    private void register(User user) {
        user.setPassword(authPersistencePort.encodePassword(user.getPassword()));
        userServicePort.createUser(user);
    }
}