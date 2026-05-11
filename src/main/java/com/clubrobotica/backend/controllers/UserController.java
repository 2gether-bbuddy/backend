package com.clubrobotica.backend.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.clubrobotica.backend.models.User;
import com.clubrobotica.backend.services.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;
    public UserController(
        UserService userService){
        this.userService = userService;
    }
    
    @GetMapping
    public List<User> getAll(){
        return userService.getUsers();
    }
    
    @PostMapping
    public User save(
        @RequestBody User user){
        return userService.saveUser(user);
    }
}
