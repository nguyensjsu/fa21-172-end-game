package com.example.users.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Users")
public class User 
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
     
    @Column(nullable = false, unique = true, length = 45)
    private String email;
    
    @Column(nullable = false, length = 64)
    private String password;
     
    @Column(name = "user_name", nullable = false, unique = true, length = 20)
    private String userName;
    
    // Constructor for user
    public User(String email, String password, String userName) 
    {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }
    
    @Override
    public String toString() 
    {
        return "User{" + "id=" + this.id + ", email='" + this.email 
        + '\'' + ", password='" + this.password + '\'' + this.userName + '\'' + '}';
    }
}
