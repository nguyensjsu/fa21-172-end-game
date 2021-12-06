package com.example.users;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity // Use 
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
     
    @Column(name = "user_name", nullable = false, length = 20)
    private String userName;
    
    User()
    {

    }
    // Constructor for user
    public User(String email, String password, String userName) 
    {
        super();
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (this == o)
        {
            return true;
        }
        
        if (!(o instanceof User))
        {
            return false;
        }
        User user = (User) o;
        return Objects.equals(this.id, user.id) 
        && Objects.equals(this.email, user.email) 
        && Objects.equals(this.password, user.password) 
        && Objects.equals(this.userName, user.userName);
    }
  
    @Override
    public int hashCode() 
    {
        return Objects.hash(this.id, this.email, this.password, this.userName);
    }
  
    @Override
    public String toString() 
    {
        return "User{" + "id=" + this.id + ", email='" + this.email 
        + '\'' + ", password='" + this.password + '\'' + this.userName + '\'' + '}';
    }
}
