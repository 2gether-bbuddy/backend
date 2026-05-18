package com.clubrobotica.backend.security;

import com.clubrobotica.backend.dto.*;
import com.clubrobotica.backend.models.User;
import com.clubrobotica.backend.models.Career;
import com.clubrobotica.backend.models.Application;
import com.clubrobotica.backend.repositories.UserRepository;
import com.clubrobotica.backend.repositories.CareerRepository;
import com.clubrobotica.backend.repositories.ApplicationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.clubrobotica.backend.security.JwtUtil;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserRepository userRepository;
    private final CareerRepository careerRepository;
    private final ApplicationRepository applicationRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(
            UserRepository userRepository,
            CareerRepository careerRepository,
            ApplicationRepository applicationRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.careerRepository = careerRepository;
        this.applicationRepository = applicationRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){

        // 1. Buscamos si el usuario ya existe
        Optional<User> existingUser = userRepository.findByControlNumber(request.getControlNumber());

        if (existingUser.isPresent()) {
            User user = existingUser.get();

            // 2. Si ya existe, buscamos su solicitud vinculada
            // (Asumiendo que findByUser es un método en tu ApplicationRepository)
            Application existingApp = applicationRepository.findByUser(user)
                    .orElseThrow(() -> new RuntimeException("Usuario existe pero no tiene solicitud."));

            // 3. Verificamos si fue rechazado
            if ("RECHAZADO".equals(existingApp.getState())) {
                // REUTILIZAR CUENTA: Actualizamos datos por si cambiaron
                user.setName(request.getName());
                user.setEmail(request.getEmail());
                user.setPhone(request.getPhone());
                user.setPassword(passwordEncoder.encode(request.getPassword()));

                if (request.getCareer() != null) {
                    Career carreraReal = careerRepository.findById(request.getCareer().getIdCareer())
                            .orElseThrow(() -> new RuntimeException("La carrera no existe."));
                    user.setCareer(carreraReal);
                }
                userRepository.save(user);

                // ACTUALIZAR SOLICITUD: La volvemos a poner en PENDIENTE
                existingApp.setSkills(request.getSkills());
                existingApp.setReason(request.getReason());
                existingApp.setProjects(request.getProjects());
                existingApp.setState("PENDIENTE");
                applicationRepository.save(existingApp);

                return ResponseEntity.ok("Tu solicitud previa ha sido actualizada y enviada de nuevo a revisión.");
            } else {
                // Si está PENDIENTE o ACEPTADO, no lo dejamos registrarse de nuevo
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Este número de control ya tiene una solicitud activa o ya es miembro.");
            }
        }

        // --- REGISTRO NUEVO (Si no existía el usuario) ---
        User user = new User();
        user.setControlNumber(request.getControlNumber());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setRole(User.Role.ALUMNO);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if (request.getCareer() != null) {
            Career carreraReal = careerRepository.findById(request.getCareer().getIdCareer())
                    .orElseThrow(() -> new RuntimeException("La carrera no existe."));
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

        return ResponseEntity.ok("Usuario y solicitud registrados con éxito.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        User user = userRepository.findByControlNumber(request.getControlNumber())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        boolean valid = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!valid){
            throw new RuntimeException("Password incorrecta");
        }

        if (user.getRole() == User.Role.ALUMNO) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Acceso denegado: Tu solicitud de ingreso aún está siendo revisada por la directiva.");
        }

        String token = jwtUtil.generateToken(user.getControlNumber());
        return ResponseEntity.ok(new AuthResponse(token, user.getRole().name(), user.getName()));
    }
}