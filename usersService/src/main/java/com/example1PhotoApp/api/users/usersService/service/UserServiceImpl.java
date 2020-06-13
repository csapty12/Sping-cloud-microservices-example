package com.example1PhotoApp.api.users.usersService.service;

import com.example1PhotoApp.api.users.usersService.entity.UserEntity;
import com.example1PhotoApp.api.users.usersService.model.UserCommand;
import com.example1PhotoApp.api.users.usersService.model.UserResponse;
import com.example1PhotoApp.api.users.usersService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserResponse createUser(UserCommand userCommand) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userCommand.getFirstName());
        userEntity.setLastName(userCommand.getLastName());
        userEntity.setEmail(userCommand.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userCommand.getPassword()));
        UserEntity savedUser = userRepository.save(userEntity);


        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setPassword(savedUser.getPassword());
        userResponse.setEmail(savedUser.getEmail());

        return userResponse;
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .map(entity -> {
                    UserResponse userResponse = new UserResponse();
                    userResponse.setId(entity.getId());
                    userResponse.setFirstName(entity.getFirstName());
                    userResponse.setLastName(entity.getLastName());
                    userResponse.setPassword(entity.getPassword());
                    return userResponse;
                })
        .orElseThrow(()-> new UsernameNotFoundException("user with email: " + email  + " not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with email : " + email + " not found."));
        return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                true,
                true,
                true,
                true,
                new ArrayList<>()
        );
    }
}
