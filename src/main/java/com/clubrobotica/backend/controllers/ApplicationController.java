package com.clubrobotica.backend.controllers;

import com.clubrobotica.backend.models.Application;
import com.clubrobotica.backend.services.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
@CrossOrigin("*")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // Ruta para obtener la lista de espera
    @GetMapping("/pending")
    public List<Application> getPending() {
        return applicationService.getPendingApplications();
    }

    // Ruta para aprobar (PUT porque estamos actualizando un registro existente)
    @PutMapping("/{id}/approve")
    public Application approve(@PathVariable Integer id) {
        return applicationService.approveApplication(id);
    }

    // Ruta para rechazar
    @PutMapping("/{id}/reject")
    public Application reject(@PathVariable Integer id) {
        return applicationService.rejectApplication(id);
    }
}