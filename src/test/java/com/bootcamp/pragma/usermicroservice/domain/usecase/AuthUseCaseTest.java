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
    void shouldRegisterAuxWarehouse() {
        User user = UserTestUtil.generateUser();
        Role role = RoleTestUtil.generateRole();

        when(roleServicePort.findRoleByName(role.getName())).thenReturn(role);
        when(authPersistencePort.encodePassword(user.getPassword())).thenReturn("password");

        authUseCase.registerAuxWarehouse(user);
        verify(roleServicePort).findRoleByName(role.getName());
        verify(authPersistencePort).encodePassword("password");
    }

    @Test
    void shouldLoginSuccessfully(){
        User user = UserTestUtil.generateUser();
        Role role = RoleTestUtil.generateRole();
        user.setRole(role);
        when(userServicePort.findUserByEmail(user.getEmail())).thenReturn(user);
        when(authPersistencePort.matchPassword(user.getPassword(), user.getPassword())).thenReturn(true);
        when(authPersistencePort.loginUser(user)).thenReturn("token");
        authUseCase.login(user.getEmail(), user.getPassword());
        verify(authPersistencePort).matchPassword(user.getPassword(), user.getPassword());
        verify(authPersistencePort).loginUser(user);
    }
}
