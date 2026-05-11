package com.clubrobotica.backend.security;

import com.clubrobotica.backend.dto.*;
import com.clubrobotica.backend.models.User;
import com.clubrobotica.backend.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.clubrobotica.backend.security.JwtUtil;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    
    public AuthController(
        UserRepository userRepository,
            PasswordEncoder passwordEnconder,
            JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEnconder;
        this.jwtUtil = jwtUtil;
    }
    
    @PostMapping("/register")
    public User register(
    @RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    @PostMapping("/login")
    public AuthResponse login(
    @RequestBody LoginRequest request){
        User user = userRepository.findByControlNumber(request.getControlNumber()).orElseThrow();
        boolean valid = passwordEncoder.matches(request.getPassword(),user.getPassword());
        if (!valid){
            throw new RuntimeException("Password incorrecta");
        }
        
        String token = jwtUtil.generateToken(user.getControlNumber());
        
        return new AuthResponse(token);
    }
}
