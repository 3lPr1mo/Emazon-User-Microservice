package com.bootcamp.pragma.usermicroservice.domain.usecase;

import com.bootcamp.pragma.usermicroservice.domain.api.usecase.UserUseCase;
import com.bootcamp.pragma.usermicroservice.domain.exception.UserAlreadyExistException;
import com.bootcamp.pragma.usermicroservice.domain.model.User;
import com.bootcamp.pragma.usermicroservice.domain.spi.IUserPersistencePort;
import com.bootcamp.pragma.usermicroservice.domain.usecase.util.UserTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserUseCaseTest {
    @Mock
    private IUserPersistencePort userPersistencePort;
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userUseCase = new UserUseCase(userPersistencePort);
    }

    @Test
    void shouldSaveUserWhenNotExists() {
        User user = UserTestUtil.generateUser();
        userUseCase.createUser(user);
        verify(userPersistencePort, times(1)).save(user);
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {
        User user = UserTestUtil.generateUser();
        when(userPersistencePort.findByEmail(user.getEmail())).thenReturn(Optional.of(UserTestUtil.generateUser()));
        assertThrows(UserAlreadyExistException.class, () -> userUseCase.createUser(user));
    }
}
