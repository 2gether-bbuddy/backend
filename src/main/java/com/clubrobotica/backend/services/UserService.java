package com.clubrobotica.backend.services;

import org.springframework.stereotype.Service;
import com.clubrobotica.backend.models.User;
import com.clubrobotica.backend.repositories.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    //Obtener todos
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    
    //Guardar
    public User saveUser(User user){
        return userRepository.save(user);
    }
    
    //Buscar por numero de control
    public Optional<User> getControlNumber(String control_number){
        return userRepository.findByControlNumber(control_number);
    }
    
    //Por correo
    public Optional<User> getEmail(String email){
        return userRepository.findByEmail(email);
    }
    
    //Eliminar
    public void deleteUser(String control_number){
        userRepository.deleteByControlNumber(control_number);
    }

    // NUEVO MÉTODO: Baja Lógica
    public User darDeBaja(String controlNumber) {
        User user = userRepository.findByControlNumber(controlNumber)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setRole(User.Role.BAJA);
        return userRepository.save(user);
    }
}
