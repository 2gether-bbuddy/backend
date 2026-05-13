package com.clubrobotica.backend.security;

import com.clubrobotica.backend.dto.*;
import com.clubrobotica.backend.models.User;
import com.clubrobotica.backend.models.Career;
import com.clubrobotica.backend.models.Application; // <-- Importamos la Solicitud
import com.clubrobotica.backend.repositories.UserRepository;
import com.clubrobotica.backend.repositories.CareerRepository;
import com.clubrobotica.backend.repositories.ApplicationRepository; // <-- Importamos el repositorio
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.clubrobotica.backend.security.JwtUtil;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserRepository userRepository;
    private final CareerRepository careerRepository;
    private final ApplicationRepository applicationRepository; // <-- Añadido
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // Actualizamos el constructor para inyectar ApplicationRepository
    public AuthController(
            UserRepository userRepository,
            CareerRepository careerRepository,
            ApplicationRepository applicationRepository, // <-- Añadido
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.careerRepository = careerRepository;
        this.applicationRepository = applicationRepository; // <-- Añadido
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request){

        // CORRECCIÓN 1: Usamos findByControlNumber en lugar de findById
        if (userRepository.findByControlNumber(request.getControlNumber()).isPresent()) {
            throw new RuntimeException("El número de control ya está registrado.");
        }

        User user = new User();
        user.setControlNumber(request.getControlNumber());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        // CORRECCIÓN 2: Usamos el Enum oficial de tu clase User
        user.setRole(User.Role.ALUMNO);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if (request.getCareer() != null) {
            Career carreraReal = careerRepository.findById(request.getCareer().getIdCareer())
                    .orElseThrow(() -> new RuntimeException("Error: La carrera especificada no existe."));
            user.setCareer(carreraReal);
        }

        userRepository.save(user);

        Application app = new Application();
        app.setSkills(request.getSkills());
        app.setReason(request.getReason());
        app.setProjects(request.getProjects());
        app.setState("PENDIENTE");
        app.setUser(user);

        applicationRepository.save(app);

        return "Usuario y solicitud registrados con éxito.";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request){
        User user = userRepository.findByControlNumber(request.getControlNumber()).orElseThrow();
        boolean valid = passwordEncoder.matches(request.getPassword(),user.getPassword());
        if (!valid){
            throw new RuntimeException("Password incorrecta");
        }

        String token = jwtUtil.generateToken(user.getControlNumber());

        return new AuthResponse(token, user.getRole().name(), user.getName());
    }
}