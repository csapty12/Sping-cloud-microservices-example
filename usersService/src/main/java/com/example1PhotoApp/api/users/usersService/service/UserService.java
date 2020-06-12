package com.example1PhotoApp.api.users.usersService.service;

import com.example1PhotoApp.api.users.usersService.model.UserCommand;
import com.example1PhotoApp.api.users.usersService.model.UserResponse;

public interface UserService {

    UserResponse createUser(UserCommand userCommand);
}
