package com.bootcamp.pragma.usermicroservice.configuration;

import com.bootcamp.pragma.usermicroservice.domain.api.IRoleServicePort;
import com.bootcamp.pragma.usermicroservice.domain.api.usecase.RoleUseCase;
import com.bootcamp.pragma.usermicroservice.domain.spi.IRolePersistencePort;
import com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.adapter.RoleJpaAdapter;
import com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RoleBeanConfiguration {
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IRoleServicePort roleServicePort() {
        return new RoleUseCase(rolePersistencePort());
    }
}
