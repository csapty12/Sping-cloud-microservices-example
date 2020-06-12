package com.example1PhotoApp.api.users.usersService.service;

import com.example1PhotoApp.api.users.usersService.entity.UserEntity;
import com.example1PhotoApp.api.users.usersService.model.User;
import com.example1PhotoApp.api.users.usersService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userRepository.save(userEntity);
        return user;
    }
}
