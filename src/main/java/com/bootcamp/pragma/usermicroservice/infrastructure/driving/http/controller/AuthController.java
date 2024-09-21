package com.bootcamp.pragma.usermicroservice.infrastructure.driving.http.controller;

import com.bootcamp.pragma.usermicroservice.application.dto.request.LoginUser;
import com.bootcamp.pragma.usermicroservice.application.dto.request.RegisterUser;
import com.bootcamp.pragma.usermicroservice.application.dto.response.LoggedUserResponse;
import com.bootcamp.pragma.usermicroservice.application.handler.AuthHandler;
import com.bootcamp.pragma.usermicroservice.infrastructure.driving.http.controller.util.SwaggerConstants;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthHandler authHandler;

    @PostMapping("register/aux")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SwaggerConstants.OK_STATUS_CODE , description = SwaggerConstants.USER_REGISTERED_SUCCESSFULLY_MSG),
            @ApiResponse(responseCode = SwaggerConstants.BAD_REQUEST_STATUS_CODE, description = SwaggerConstants.BAD_REQUEST_MSG),
    })
    public ResponseEntity<Void> registerAuxWarehouse(@Valid @RequestBody RegisterUser registerUser) {
        authHandler.saveAuxUser(registerUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("login")
    public ResponseEntity<LoggedUserResponse> loginUser(@Valid @RequestBody LoginUser loginUser) {
        return ResponseEntity.status(HttpStatus.OK).body(authHandler.loginUser(loginUser));
    }

}
