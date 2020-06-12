package com.example1PhotoApp.api.users.usersService.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class UserResponse {

    private Integer id;

    @NotNull(message = "first name must not be empty")
    private String firstName;

    @NotNull(message = "last name must not be empty")
    private String lastName;

    @NotNull(message = "password must not be empty")
    private String password;

    @NotNull(message = "email must not be empty")
    private String email;


}
