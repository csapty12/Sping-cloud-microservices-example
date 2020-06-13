package com.example1PhotoApp.api.users.usersService.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginCommand {
    @NotNull(message = "password must not be empty")
    private String password;

    @NotNull(message = "email must not be empty")
    private String email;
}
