package com.clubrobotica.backend.services;

import com.clubrobotica.backend.models.Application;
import com.clubrobotica.backend.models.User;
import com.clubrobotica.backend.repositories.ApplicationRepository;
import com.clubrobotica.backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;

    public ApplicationService(ApplicationRepository applicationRepository, UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
    }

    public List<Application> getPendingApplications() {
        return applicationRepository.findByState("PENDIENTE");
    }

    public Application approveApplication(Integer id) {
        Application app = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        // 1. Cambiamos el estado de la solicitud
        app.setState("ACEPTADO");

        // 2. Le damos el "ascenso" al usuario
        User user = app.getUser();
        if (user != null) {
            // Nota: Asegúrate de que "MIEMBRO" exista en tu Enum de roles en User.java
            user.setRole(User.Role.MIEMBRO);
            userRepository.save(user);
        }

        return applicationRepository.save(app);
    }

    public Application rejectApplication(Integer id) {
        Application app = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        app.setState("RECHAZADO");
        return applicationRepository.save(app);
    }
}