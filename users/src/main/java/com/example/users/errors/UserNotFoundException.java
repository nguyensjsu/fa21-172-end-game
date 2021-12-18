package com.example.users.errors;

class UserNotFoundException extends RuntimeException 
{
    UserNotFoundException(Long id) 
    {
        super("Could not find user " + id);
    }
}