package com.bootcamp.pragma.usermicroservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoggedUserResponse {
    private String token;
}
