package com.example.users;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

 
@Entity
@Table(name = "users")
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
    
    /* Not sure if this is needed in this class
    User()
    {

    }
    */

    public User(String email, String password, String userName) 
    {
        super();
        this.email = email;
        this.password = password;
        this.userName = userName;
    }


    public Long getId() 
    {
        return this.id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }
    
    public String getEmail() 
    {
        return email;
    }
    
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getPassword() 
    {
        return password;
    }
    
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getUserName() 
    {
        return userName;
    }
    
    public void setUserName(String userName) 
    {
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
        return Objects.equals(this.id, user.id) && Objects.equals(this.email, user.email) && Objects.equals(this.password, user.password) && Objects.equals(this.userName, user.userName);
    }
  
    @Override
    public int hashCode() 
    {
        return Objects.hash(this.id, this.email, this.password, this.userName);
    }
  
    @Override
    public String toString() 
    {
        return "User{" + "id=" + this.id + ", email='" + this.email + '\'' + ", password='" + this.password + '\'' + this.userName + '\'' + '}';
    }
}
