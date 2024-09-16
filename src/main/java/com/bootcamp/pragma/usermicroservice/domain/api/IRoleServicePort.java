package com.bootcamp.pragma.usermicroservice.domain.api;

import com.bootcamp.pragma.usermicroservice.domain.model.Role;

public interface IRoleServicePort {
    Role findRoleByName(String name);
}
