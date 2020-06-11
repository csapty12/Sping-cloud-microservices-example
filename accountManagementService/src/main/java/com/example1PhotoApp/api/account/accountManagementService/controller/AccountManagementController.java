package com.example1PhotoApp.api.account.accountManagementService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountManagementController {


    @GetMapping("/status")
    public String getStatus() {
        return "Account Management Service Working";
    }
}
