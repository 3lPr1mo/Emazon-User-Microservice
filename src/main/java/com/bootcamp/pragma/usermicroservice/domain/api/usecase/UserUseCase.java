package com.bootcamp.pragma.usermicroservice.domain.api.usecase;

import com.bootcamp.pragma.usermicroservice.domain.api.IUserServicePort;
import com.bootcamp.pragma.usermicroservice.domain.exception.UserAlreadyExistException;
import com.bootcamp.pragma.usermicroservice.domain.exception.UserIsUnderAgeException;
import com.bootcamp.pragma.usermicroservice.domain.exception.UserNotExistException;
import com.bootcamp.pragma.usermicroservice.domain.model.User;
import com.bootcamp.pragma.usermicroservice.domain.spi.IUserPersistencePort;
import com.bootcamp.pragma.usermicroservice.domain.util.DomainConstants;

import java.util.Calendar;
import java.util.Date;

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
        if(!isAdult(user.getDateBirth())){
            throw new UserIsUnderAgeException(DomainConstants.USER_IS_UNDER_AGE);
        }
        userPersistencePort.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userPersistencePort.findByEmail(email).orElseThrow(() -> new UserNotExistException(DomainConstants.USER_NOT_FOUND));
    }

    private boolean isAdult(Date date) {
        Calendar calendarBirth = Calendar.getInstance();
        calendarBirth.setTime(date);
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTime(new Date());
        return calendarNow.get(Calendar.YEAR) - calendarBirth.get(Calendar.YEAR) >= 18;
    }
}
