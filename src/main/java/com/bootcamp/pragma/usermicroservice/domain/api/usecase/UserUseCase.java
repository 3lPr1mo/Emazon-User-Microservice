package com.bootcamp.pragma.usermicroservice.domain.api.usecase;

import com.bootcamp.pragma.usermicroservice.domain.api.IUserServicePort;
import com.bootcamp.pragma.usermicroservice.domain.exception.UserAlreadyExistException;
import com.bootcamp.pragma.usermicroservice.domain.model.User;
import com.bootcamp.pragma.usermicroservice.domain.spi.IUserPersistencePort;
import com.bootcamp.pragma.usermicroservice.domain.util.DomainConstants;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void createUser(User user) {
        if(userPersistencePort.findById(user.getId()).isPresent()){
            throw new UserAlreadyExistException(DomainConstants.USER_ALREADY_EXISTS + user.getId());
        }
        if(userPersistencePort.findByEmail(user.getEmail()).isPresent()){
            throw new UserAlreadyExistException(DomainConstants.USER_ALREADY_EXISTS + user.getEmail());
        }
        userPersistencePort.save(user);
    }
}
