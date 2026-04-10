package com.clubrobotica.backend.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.clubrobotica.backend.models.User;
import com.clubrobotica.backend.services.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;
    
    @PostMapping
    public User create(@RequestBody User u){
        return service.save(u);
    }
    
    @GetMapping
    public List<User> getAll(){
        return service.repo.findAll();
    }
}
