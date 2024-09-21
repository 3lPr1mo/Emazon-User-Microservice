package com.bootcamp.pragma.usermicroservice.application.dto.request;

import com.bootcamp.pragma.usermicroservice.application.util.AuthConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Builder
@Getter
public class RegisterUser {
    @NotNull(message = AuthConstants.ID_IS_REQUIRED_MSG)
    private Long id;

    @NotBlank(message = AuthConstants.FIRST_NAME_IS_REQUIRED_MSG)
    private String firstname;

    @NotBlank(message = AuthConstants.LAST_NAME_IS_REQUIRED_MSG)
    private String lastname;

    @NotBlank(message = AuthConstants.EMAIL_IS_REQUIRED_MSG)
    @Email(message = AuthConstants.EMAIL_IS_NOT_VALID_MSG)
    private String email;

    @NotBlank(message = AuthConstants.PHONE_IS_REQUIRED_MSG)
    private String phone;

    private Date dateBirth;

    @NotBlank(message = AuthConstants.PASSWORD_IS_REQUIRED_MSG)
    private String password;
}
