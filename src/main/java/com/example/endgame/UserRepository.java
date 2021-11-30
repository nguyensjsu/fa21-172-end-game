package com.example.endgame;

import org.springframework.data.repository.CrudRepository;

import com.example.endgame.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
