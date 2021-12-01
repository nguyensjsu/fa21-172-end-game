package com.example.endgame;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController 
{

    private final UserRepository repository;
        
    UserController(UserRepository repository)
    {
        this.repository = repository;
    }

    // Get all users
    @GetMapping("/users")
    public List<User> findAllUsers() 
    {
        return repository.findAll();
    }

    // Post a new user
    @PostMapping("/users")
    User newUser(@RequestBody User newUser) 
    {
        return repository.save(newUser);
    }

    // Get a single user
    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) 
    {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) 
    {
        
        return repository.findById(id).map(user -> {
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            user.setUserName(newUser.getUserName());
            return repository.save(user);
        })
        .orElseGet(() -> {
            newUser.setId(id);
            return repository.save(newUser);
        });
    }

    @DeleteMapping("/users/{id}")
    void deleteEmployee(@PathVariable Long id) 
    {
        repository.deleteById(id);
    }
}
