package com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.adapter;

import com.bootcamp.pragma.usermicroservice.domain.model.User;
import com.bootcamp.pragma.usermicroservice.domain.spi.IAuthPersistencePort;
import com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.mapper.IUserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class AuthAdapter implements IAuthPersistencePort {

    private final PasswordEncoder passwordEncoder;
    private final JwtAdapter jwtAdapter;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matchPassword(String password, String encryptedPassword) {
        return passwordEncoder.matches(password, encryptedPassword);
    }

    @Override
    public String loginUser(User user) {
        return jwtAdapter.generateToken(userEntityMapper.toEntityFromModel(user));
    }
}
