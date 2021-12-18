package com.example.users.service;

import com.example.users.entity.User;
import com.example.users.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }
}
