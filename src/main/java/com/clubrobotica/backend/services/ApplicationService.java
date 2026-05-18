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
        // Traemos solo los que siguen pendientes
        return applicationRepository.findByState("PENDIENTE");
    }

    public Application approveApplication(Integer id) {
        Application app = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        // 1. Cambiamos el estado de la solicitud
        app.setState("ACEPTADO");

        // 2. Le damos el "ascenso" al usuario (¡Blindaje anti-degradación activado!)
        User user = app.getUser();
        // Verificamos que el usuario exista y que no tenga ya un cargo superior
        if (user != null && user.getRole() != User.Role.PRESIDENTE && user.getRole() != User.Role.MESA) {
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