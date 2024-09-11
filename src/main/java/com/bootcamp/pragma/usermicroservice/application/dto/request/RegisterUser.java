package com.bootcamp.pragma.usermicroservice.application.dto.request;

import com.bootcamp.pragma.usermicroservice.application.util.AuthConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Builder
public class RegisterUser {
    @NotBlank(message = AuthConstants.ID_IS_REQUIRED_MSG)
    private Long id;

    @NotBlank(message = AuthConstants.FIRST_NAME_IS_REQUIRED_MSG)
    private String firstname;

    @NotBlank(message = AuthConstants.LAST_NAME_IS_REQUIRED_MSG)
    private String lastname;

    @NotBlank(message = AuthConstants.EMAIL_IS_REQUIRED_MSG)
    private String email;

    @NotBlank(message = AuthConstants.PHONE_IS_REQUIRED_MSG)
    private String phone;

    @NotBlank(message = AuthConstants.DATE_OF_BIRTH_IS_REQUIRED_MSG)
    private Date dateBirth;

    @NotBlank(message = AuthConstants.PASSWORD_IS_REQUIRED_MSG)
    private String password;
}
