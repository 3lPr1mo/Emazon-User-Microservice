package com.bootcamp.pragma.usermicroservice.configuration;

import com.bootcamp.pragma.usermicroservice.domain.api.IUserServicePort;
import com.bootcamp.pragma.usermicroservice.domain.api.usecase.UserUseCase;
import com.bootcamp.pragma.usermicroservice.domain.spi.IUserPersistencePort;
import com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.adapter.UserJpaAdapter;
import com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserBeanConfiguration {
    private final IUserEntityMapper userEntityMapper;
    private final IUserRepository userRepository;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }

}
