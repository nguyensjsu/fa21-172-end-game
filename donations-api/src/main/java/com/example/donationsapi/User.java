package com.example.donationsapi;

import lombok.Data;
import lombok.RequiredArgsConstructor;
// 
@Data
@RequiredArgsConstructor
public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String newPassword;
}
