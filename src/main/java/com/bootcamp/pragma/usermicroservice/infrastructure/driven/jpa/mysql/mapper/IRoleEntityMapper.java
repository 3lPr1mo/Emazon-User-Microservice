package com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.mapper;

import com.bootcamp.pragma.usermicroservice.domain.model.Role;
import com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleEntityMapper {
    Role toModelFromEntity(RoleEntity roleEntity);
    RoleEntity toEntityFromModel(Role role);
}
