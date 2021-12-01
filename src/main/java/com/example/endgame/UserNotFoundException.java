package com.example.endgame;

class UserNotFoundException extends RuntimeException {

    UserNotFoundException(Long id) {
      super("Could not find user " + id);
    }
  }