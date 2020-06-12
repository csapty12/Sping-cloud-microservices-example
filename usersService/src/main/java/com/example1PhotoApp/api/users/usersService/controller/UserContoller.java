package com.example1PhotoApp.api.users.usersService.controller;

import com.example1PhotoApp.api.users.usersService.model.User;
import com.example1PhotoApp.api.users.usersService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserContoller {

    @Autowired
    private Environment environment;

    @Autowired
    private UserService userService;

    @GetMapping("/status")
    public String getStatus() {
        return "User Service Working on port: " + environment.getProperty("local.server.port");
    }

    @PostMapping("/create")
    public User createUser(@Valid @RequestBody User user){
        System.out.println("user details " + user);

        return userService.createUser(user);
    }
}
