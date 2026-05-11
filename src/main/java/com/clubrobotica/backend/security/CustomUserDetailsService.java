package com.clubrobotica.backend.security;

import com.clubrobotica.backend.repositories.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    private final UserRepository userRepository;
    public CustomUserDetailsService(
        UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String controlNumber) throws UsernameNotFoundException {
        com.clubrobotica.backend.models.User user = userRepository.findByControlNumber(controlNumber)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado")
                ); 
        
        
        return new org.springframework.security.core.userdetails.User(user.getPassword(), user.getControlNumber(), Collections.emptyList());
    }
}
