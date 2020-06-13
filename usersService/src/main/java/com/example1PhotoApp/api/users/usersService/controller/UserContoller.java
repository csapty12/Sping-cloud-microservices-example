package com.example1PhotoApp.api.users.usersService.controller;

import com.example1PhotoApp.api.users.usersService.model.UserCommand;
import com.example1PhotoApp.api.users.usersService.model.UserResponse;
import com.example1PhotoApp.api.users.usersService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserContoller {

    @Autowired
    private Environment environment;

    @Autowired
    private UserService userService;

    @GetMapping("/status")
    public String getStatus() {
        return "User Service Working on port: " + environment.getProperty("local.server.port");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody UserCommand userCommand){
        System.out.println("user details " + userCommand);
        return userService.createUser(userCommand);
    }
}
