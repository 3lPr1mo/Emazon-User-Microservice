package com.bootcamp.pragma.usermicroservice.domain.api.usecase;

import com.bootcamp.pragma.usermicroservice.domain.api.IAuthServicePort;
import com.bootcamp.pragma.usermicroservice.domain.api.IRoleServicePort;
import com.bootcamp.pragma.usermicroservice.domain.api.IUserServicePort;
import com.bootcamp.pragma.usermicroservice.domain.exception.PasswordDoNotMatchException;
import com.bootcamp.pragma.usermicroservice.domain.model.User;
import com.bootcamp.pragma.usermicroservice.domain.spi.IAuthPersistencePort;
import com.bootcamp.pragma.usermicroservice.domain.util.DomainConstants;

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

    @Override
    public String login(String email, String password) {
        User user = userServicePort.findUserByEmail(email);
        if(!authPersistencePort.matchPassword(password, user.getPassword())){
            throw new PasswordDoNotMatchException(DomainConstants.PASSWORD_DOES_NOT_MATCH);
        }
        return authPersistencePort.loginUser(user);
    }

    private void register(User user) {
        user.setPassword(authPersistencePort.encodePassword(user.getPassword()));
        userServicePort.createUser(user);
    }
}
