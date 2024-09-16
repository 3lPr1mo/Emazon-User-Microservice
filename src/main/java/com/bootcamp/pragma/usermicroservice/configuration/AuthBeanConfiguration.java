package com.bootcamp.pragma.usermicroservice.configuration;

import com.bootcamp.pragma.usermicroservice.domain.api.IAuthServicePort;
import com.bootcamp.pragma.usermicroservice.domain.api.usecase.AuthUseCase;
import com.bootcamp.pragma.usermicroservice.domain.spi.IAuthPersistencePort;
import com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.adapter.AuthAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AuthBeanConfiguration {
    private final UserBeanConfiguration userBeanConfiguration;
    private final RoleBeanConfiguration roleBeanConfiguration;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IAuthPersistencePort authPersistencePort() {
        return new AuthAdapter(passwordEncoder());
    }

    @Bean
    public IAuthServicePort authServicePort() {
        return new AuthUseCase(userBeanConfiguration.userServicePort(), authPersistencePort(), roleBeanConfiguration.roleServicePort());
    }

}
