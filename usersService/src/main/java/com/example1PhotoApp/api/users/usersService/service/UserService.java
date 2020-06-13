package com.example1PhotoApp.api.users.usersService.service;

import com.example1PhotoApp.api.users.usersService.model.UserCommand;
import com.example1PhotoApp.api.users.usersService.model.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserResponse createUser(UserCommand userCommand);
    UserResponse getUserByEmail(String email);
}
