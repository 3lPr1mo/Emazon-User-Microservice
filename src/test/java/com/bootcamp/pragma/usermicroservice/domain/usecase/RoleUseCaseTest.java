package com.bootcamp.pragma.usermicroservice.domain.usecase;

import com.bootcamp.pragma.usermicroservice.domain.api.usecase.RoleUseCase;
import com.bootcamp.pragma.usermicroservice.domain.exception.RoleNotFoundException;
import com.bootcamp.pragma.usermicroservice.domain.model.Role;
import com.bootcamp.pragma.usermicroservice.domain.spi.IRolePersistencePort;
import com.bootcamp.pragma.usermicroservice.domain.usecase.util.RoleTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RoleUseCaseTest {
    @Mock
    private IRolePersistencePort rolePersistencePort;
    private RoleUseCase roleUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        roleUseCase = new RoleUseCase(rolePersistencePort);
    }

    @Test
    void shouldReturnRoleByName(){
        Role expectedRole = RoleTestUtil.generateRole();
        when(rolePersistencePort.findByName("AUX_BODEGA")).thenReturn(Optional.of(expectedRole));
        Role actualRole = roleUseCase.findRoleByName("AUX_BODEGA");
        verify(rolePersistencePort, times(1)).findByName("AUX_BODEGA");
        assertEquals(expectedRole, actualRole);
    }

    @Test
    void shouldThrowExceptionWhenRoleNotFound(){
        when(rolePersistencePort.findByName("AUX_BODEGA1")).thenReturn(Optional.empty());
        assertThrows(RoleNotFoundException.class, ()-> roleUseCase.findRoleByName("AUX_BODEGA1"));
    }
}
