package com.clubrobotica.backend.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.clubrobotica.backend.models.User;
import com.clubrobotica.backend.services.UserService;
import com.clubrobotica.backend.repositories.UserRepository; // Importamos el repo directo por rapidez
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository; // Inyectamos el repo

    public UserController(UserService userService, UserRepository userRepository){
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getUsers();
    }

    // NUEVA RUTA: Solo miembros aceptados
    @GetMapping("/members")
    public List<User> getMembers(){
        return userRepository.findAcceptedMembers();
    }

    @PostMapping
    public User save(@RequestBody User user){
        return userService.saveUser(user);
    }
    // RUTA PARA DAR DE BAJA LÓGICA DESDE LA APP
    @PutMapping("/{controlNumber}/baja")
    public ResponseEntity<?> darDeBaja(@PathVariable String controlNumber) {
        try {
            userService.darDeBaja(controlNumber);
            return ResponseEntity.ok("El alumno ha sido dado de baja del club.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar la baja.");
        }
    }
}