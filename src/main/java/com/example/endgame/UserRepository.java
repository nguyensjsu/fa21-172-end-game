package com.example.endgame;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.endgame.User;

public interface UserRepository extends JpaRepository<User, Long> 
{

}
