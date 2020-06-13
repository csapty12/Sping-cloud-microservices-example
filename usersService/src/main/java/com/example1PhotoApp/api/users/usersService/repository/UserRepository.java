package com.example1PhotoApp.api.users.usersService.repository;

import com.example1PhotoApp.api.users.usersService.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
}
