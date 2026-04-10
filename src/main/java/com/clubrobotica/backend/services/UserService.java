package com.clubrobotica.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clubrobotica.backend.models.User;
import com.clubrobotica.backend.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    public UserRepository repo;
    
    public User save (User u){
        return repo.save(u);
    }
    
    public User findByControlNumber(String control_number){
        return repo.findByControlNumber(control_number);
    }
}
