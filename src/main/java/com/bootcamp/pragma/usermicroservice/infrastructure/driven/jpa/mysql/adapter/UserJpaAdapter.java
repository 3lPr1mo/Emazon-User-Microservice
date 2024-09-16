package com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.adapter;

import com.bootcamp.pragma.usermicroservice.domain.model.User;
import com.bootcamp.pragma.usermicroservice.domain.spi.IUserPersistencePort;
import com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public void save(User user) {
        userRepository.save(userEntityMapper.toEntityFromModel(user));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userEntityMapper::toModelFromEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id).map(userEntityMapper::toModelFromEntity);
    }
}
