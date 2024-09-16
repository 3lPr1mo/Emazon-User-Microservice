package com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.adapter;

import com.bootcamp.pragma.usermicroservice.domain.spi.IAuthPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class AuthAdapter implements IAuthPersistencePort {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
