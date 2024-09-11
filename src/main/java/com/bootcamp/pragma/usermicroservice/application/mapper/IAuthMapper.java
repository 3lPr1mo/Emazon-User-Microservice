package com.bootcamp.pragma.usermicroservice.application.mapper;

import com.bootcamp.pragma.usermicroservice.application.dto.request.RegisterAuxWarehouse;
import com.bootcamp.pragma.usermicroservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IAuthMapper {
    @Mapping(target = "role", ignore = true)
    User toUserModelFromRegisterAux(RegisterAuxWarehouse auxWarehouse);
}
