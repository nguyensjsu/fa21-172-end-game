package com.example.endgame;

import javax.persistence.*;
 
@Entity
@Table(name = "users")
public class User {
     
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
     
    @Column(nullable = false, unique = true, length = 45)
    private String email;
     
    @Column(nullable = false, length = 64)
    private String password;
     
    @Column(name = "user_name", nullable = false, length = 20)
    private String userName;
     
    public void setId(Integer id) 
    {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
