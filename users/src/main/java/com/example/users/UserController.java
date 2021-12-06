package com.example.users;

import java.rmi.ServerException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import java.util.HashSet;
import org.springframework.http.ResponseEntity;
=======
>>>>>>> 5f91a27df107752134e812bd253a2e3949085bf4
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;


//import org.springframework.web.server.ResponseStatusException;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "http://localhost:3000")
@RestController // This means that this class is a RestController
@RequestMapping("/users") // This means URL's start with / (after Application path)
public class UserController 
{
    @Autowired // This means to get the bean called userRepository which is auto-generated by Spring, we will use it to handle the data
    UserRepository repository;

    /*
    private final UserRepository repository;

    UserController(UserRepository repository)
    {
        this.repository = repository;
    }
    */

    // Get all users
    @GetMapping("/all")
    public @ResponseBody List<User> findAllUsers() 
    {
        // This returns a JSON or XML with the users
        return repository.findAll();
    }
    /*
    // Add a new user
    @PostMapping("/add") 
    public @ResponseBody User addNewUser (  @RequestBody User newUser, 
                                            @RequestParam String userName, 
                                            @RequestParam String email, 
                                            @RequestParam String password) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        User n = new User();
        n.setUserName(userName);
        n.setEmail(email);
        n.setPassword(password);
        //repository.save(n);
        return repository.save(n);
    }
    */
    
    // Post a new user
    @PostMapping("/add")
    User newUser(@RequestBody User newUser) 
    {
        return repository.save(newUser);
    }
    
    // User login validation
    @GetMapping("/")
    String getUser(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password) throws ServerException{
        User user = repository.findByUserName(userName);
        HttpHeaders responseHeaders = new HttpHeaders();
        if (user == null){
            responseHeaders.set("status", HttpStatus.NOT_FOUND + "");
        }
        else if(user.getPassword().equals(password)){
            responseHeaders.set("status", HttpStatus.OK + "");
        }
        else{
            responseHeaders.set("status", HttpStatus.UNAUTHORIZED + "");

        }
        return responseHeaders.toString();
    }


    // Login a user
    @PostMapping("/login")
    String loginUser(@RequestBody User newUser) 
    {
        User user = repository.findByUserName(newUser.getUserName());
        if(user.getPassword().equals(newUser.getPassword()))
        {
            return "login";
        }
        else
        {
            return "error";
        }
    }

    // Update a user, Reset Password implementation(?)
    @PutMapping("/{id}")
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

    // Delete user
    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Long id) 
    {
        repository.deleteById(id);
    }
}
