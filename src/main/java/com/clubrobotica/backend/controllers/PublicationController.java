package com.clubrobotica.backend.controllers;

import com.clubrobotica.backend.models.Publication;
import com.clubrobotica.backend.models.User;
import com.clubrobotica.backend.models.InscriptionPublication;
import com.clubrobotica.backend.services.PublicationService;
import com.clubrobotica.backend.services.InscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/publications")
@CrossOrigin("*")
public class PublicationController {

    private final PublicationService publicationService;
    private final InscriptionService inscriptionService; // <-- Inyectamos tu servicio

    public PublicationController(PublicationService publicationService, InscriptionService inscriptionService){
        this.publicationService = publicationService;
        this.inscriptionService = inscriptionService;
    }

    @GetMapping
    public List<Publication> getAll(){
        return publicationService.getPublication();
    }

    @PostMapping
    public Publication save(@RequestBody Publication publication){
        return publicationService.savePublication(publication);
    }

    @PutMapping("/{id}/archive")
    public ResponseEntity<String> archivePublication(@PathVariable Integer id) {
        boolean archived = publicationService.archivePublication(id);
        if (archived) {
            return ResponseEntity.ok("Publicación archivada correctamente.");
        } else {
            return ResponseEntity.badRequest().body("No se pudo archivar la publicación.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publication> update(@PathVariable Integer id, @RequestBody Publication publication) {
        try {
            Publication updated = publicationService.updatePublication(id, publication);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/participants")
    public ResponseEntity<List<User>> getParticipants(@PathVariable Integer id) {
        List<User> participants = publicationService.getParticipants(id);
        return ResponseEntity.ok(participants);
    }

    // =========================================================
    // NUEVA RUTA: Recibe la inscripción desde React Native
    // =========================================================
    @PostMapping("/{id}/enroll")
    public ResponseEntity<?> enroll(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        try {
            String controlNumber = body.get("controlNumber");

            // Creamos las entidades "cáscarón" con los IDs para satisfacer a tu servicio
            User userObj = new User();
            userObj.setControlNumber(controlNumber);

            Publication pubObj = new Publication();
            pubObj.setIdPublication(id);

            // Armamos el objeto InscriptionPublication
            InscriptionPublication inscription = new InscriptionPublication();
            inscription.setUsuario(userObj);
            inscription.setPublicacion(pubObj);
            inscription.setPhysical_assistance(false); // Por defecto en false (si es booleano no nulo en tu BD)

            // Se lo pasamos a tu servicio
            inscriptionService.saveInscription(inscription);

            return ResponseEntity.ok("Inscrito exitosamente");
        } catch (RuntimeException e) {
            // Atrapamos las excepciones personalizadas que pusiste en tu servicio
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}