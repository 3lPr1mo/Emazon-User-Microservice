package com.bootcamp.pragma.usermicroservice.application.handler;

import com.bootcamp.pragma.usermicroservice.application.dto.request.RegisterAuxWarehouse;
import com.bootcamp.pragma.usermicroservice.application.mapper.IAuthMapper;
import com.bootcamp.pragma.usermicroservice.domain.api.IAuthServicePort;
import com.bootcamp.pragma.usermicroservice.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthHandler {

    private final IAuthServicePort authServicePort;
    private final IAuthMapper authMapper;

    public void saveAuxUser(RegisterAuxWarehouse auxWarehouse) {
        authServicePort.registerAuxWarehouse(authMapper.toUserModelFromRegisterAux(auxWarehouse));
    }

}
