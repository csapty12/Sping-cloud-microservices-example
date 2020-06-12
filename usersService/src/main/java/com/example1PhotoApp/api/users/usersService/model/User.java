package com.example1PhotoApp.api.users.usersService.model;

import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
public class User {

    private Integer id;

    @NotNull(message = "first name must not be empty")
    private String firstName;

    @NotNull(message = "last name must not be empty")
    private String lastName;

    @NotNull(message = "password must not be empty")
    private String password;

    @NotNull(message = "email must not be empty")
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

