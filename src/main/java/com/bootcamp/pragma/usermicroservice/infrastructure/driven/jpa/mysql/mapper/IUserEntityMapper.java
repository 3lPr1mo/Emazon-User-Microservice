package com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.mapper;

import com.bootcamp.pragma.usermicroservice.domain.model.User;
import com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IRoleEntityMapper.class})
public interface IUserEntityMapper {
    UserEntity toEntityFromModel(User user);
    User toModelFromEntity(UserEntity userEntity);
}
