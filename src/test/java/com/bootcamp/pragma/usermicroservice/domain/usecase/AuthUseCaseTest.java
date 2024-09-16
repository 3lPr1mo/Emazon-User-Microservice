package com.bootcamp.pragma.usermicroservice.domain.usecase;

import com.bootcamp.pragma.usermicroservice.domain.api.IRoleServicePort;
import com.bootcamp.pragma.usermicroservice.domain.api.IUserServicePort;
import com.bootcamp.pragma.usermicroservice.domain.api.usecase.AuthUseCase;
import com.bootcamp.pragma.usermicroservice.domain.model.Role;
import com.bootcamp.pragma.usermicroservice.domain.model.User;
import com.bootcamp.pragma.usermicroservice.domain.spi.IAuthPersistencePort;
import com.bootcamp.pragma.usermicroservice.domain.usecase.util.RoleTestUtil;
import com.bootcamp.pragma.usermicroservice.domain.usecase.util.UserTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthUseCaseTest {
    @Mock
    private IAuthPersistencePort authPersistencePort;
    @Mock
    private IUserServicePort userServicePort;
    @Mock
    private IRoleServicePort roleServicePort;
    private AuthUseCase authUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.authUseCase = new AuthUseCase(userServicePort, authPersistencePort, roleServicePort);
    }

    @Test
    public void shouldRegisterAuxWarehouse() {
        User user = UserTestUtil.generateUser();
        Role role = RoleTestUtil.generateRole();

        when(roleServicePort.findRoleByName(role.getName())).thenReturn(role);
        when(authPersistencePort.encodePassword(user.getPassword())).thenReturn("password");

        authUseCase.registerAuxWarehouse(user);
        verify(roleServicePort).findRoleByName(role.getName());
        verify(authPersistencePort).encodePassword("password");
    }
}
