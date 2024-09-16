package com.bootcamp.pragma.usermicroservice.domain.usecase.util;

import com.bootcamp.pragma.usermicroservice.domain.model.Role;

public class RoleTestUtil {
    private RoleTestUtil() {}

    public static Role generateRole() {
        return new Role(1L, "AUX_BODEGA", "Cosas de bodega");
    }
}
