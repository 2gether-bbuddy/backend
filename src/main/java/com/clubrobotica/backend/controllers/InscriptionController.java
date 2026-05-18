package com.clubrobotica.backend.controllers;

import com.clubrobotica.backend.models.InscriptionPublication;
import com.clubrobotica.backend.models.InscriptionId; // <-- ¡ESTA ES LA LÍNEA MÁGICA QUE FALTABA!
import com.clubrobotica.backend.services.InscriptionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inscriptions")
@CrossOrigin("*")
public class InscriptionController {

    private final InscriptionService service;

    public InscriptionController(InscriptionService service) {
        this.service = service;
    }

    @PostMapping
    public InscriptionPublication register(@RequestBody InscriptionPublication inscription) {
        return service.saveInscription(inscription);
    }

    // Método para preguntar si ya está inscrito
    @GetMapping("/check")
    public boolean checkInscription(
            @RequestParam("controlNumber") String controlNumber,
            @RequestParam("idPublication") Integer idPublication) {

        InscriptionId idCompuesto = new InscriptionId();
        idCompuesto.setControlNum(controlNumber);
        idCompuesto.setIdPublication(idPublication);

        // Retorna true si existe, false si no
        return service.checkIfExists(idCompuesto);
    }
}