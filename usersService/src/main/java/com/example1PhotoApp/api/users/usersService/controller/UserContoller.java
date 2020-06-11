package com.example1PhotoApp.api.users.usersService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserContoller {

    @Autowired
    private Environment environment;

    @GetMapping("/status")
    public String getStatus() {
        return "User Service Working on port: " + environment.getProperty("local.server.port");
    }
}
