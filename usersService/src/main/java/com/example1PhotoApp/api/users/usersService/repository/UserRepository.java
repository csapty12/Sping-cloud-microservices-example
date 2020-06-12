package com.example1PhotoApp.api.users.usersService.repository;

import com.example1PhotoApp.api.users.usersService.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
}
