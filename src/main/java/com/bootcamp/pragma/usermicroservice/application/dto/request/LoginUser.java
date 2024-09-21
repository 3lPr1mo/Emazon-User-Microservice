package com.bootcamp.pragma.usermicroservice.application.dto.request;

import com.bootcamp.pragma.usermicroservice.application.util.AuthConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginUser {

    @Email(message = AuthConstants.EMAIL_IS_NOT_VALID_MSG)
    private String email;
    @NotNull(message = AuthConstants.EMAIL_IS_NOT_VALID_MSG)
    private String password;
}
