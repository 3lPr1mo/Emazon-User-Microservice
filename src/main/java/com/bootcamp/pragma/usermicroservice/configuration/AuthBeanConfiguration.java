package com.bootcamp.pragma.usermicroservice.configuration;

import com.bootcamp.pragma.usermicroservice.domain.api.IAuthServicePort;
import com.bootcamp.pragma.usermicroservice.domain.api.usecase.AuthUseCase;
import com.bootcamp.pragma.usermicroservice.domain.spi.IAuthPersistencePort;
import com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.adapter.AuthAdapter;
import com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.adapter.JwtAdapter;
import com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.mapper.IUserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AuthBeanConfiguration {
    private final UserBeanConfiguration userBeanConfiguration;
    private final RoleBeanConfiguration roleBeanConfiguration;
    private final IUserEntityMapper userEntityMapper;
    private final JwtAdapter jwtAdapter;
    private final ApplicationConfiguration appConfig;

    @Bean
    public IAuthPersistencePort authPersistencePort() {
        return new AuthAdapter(appConfig.passwordEncoder(), jwtAdapter, userEntityMapper);
    }

    @Bean
    public IAuthServicePort authServicePort() {
        return new AuthUseCase(userBeanConfiguration.userServicePort(), authPersistencePort(), roleBeanConfiguration.roleServicePort());
    }

}
