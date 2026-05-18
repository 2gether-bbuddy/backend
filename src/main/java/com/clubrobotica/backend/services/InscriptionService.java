package com.clubrobotica.backend.services;

import com.clubrobotica.backend.models.InscriptionPublication;
import com.clubrobotica.backend.models.InscriptionId;
import com.clubrobotica.backend.repositories.InscriptionRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class InscriptionService {

    private final InscriptionRepository repository;

    public InscriptionService(InscriptionRepository repository) {
        this.repository = repository;
    }

    public InscriptionPublication saveInscription(InscriptionPublication inscription) {

        // --- REDES DE SEGURIDAD PARA EVITAR EL NULL POINTER EXCEPTION ---
        if (inscription.getUsuario() == null || inscription.getUsuario().getControlNumber() == null) {
            throw new RuntimeException("El objeto 'usuario' o la 'matrícula' llegaron vacíos desde el celular.");
        }
        if (inscription.getPublicacion() == null || inscription.getPublicacion().getIdPublication() == null) {
            throw new RuntimeException("El objeto 'publicacion' o el 'id' llegaron vacíos desde el celular.");
        }
        // ---------------------------------------------------------------

        // Armamos la llave compuesta
        InscriptionId idCompuesto = new InscriptionId();
        idCompuesto.setControlNum(inscription.getUsuario().getControlNumber());
        idCompuesto.setIdPublication(inscription.getPublicacion().getIdPublication());

        // Evitamos duplicados
        if (repository.existsById(idCompuesto)) {
            throw new RuntimeException("Ya estás inscrito en este evento.");
        }

        inscription.setId(idCompuesto);
        inscription.setDateInscription(LocalDateTime.now());

        return repository.save(inscription);
    }
    // Método auxiliar para verificar existencia
    public boolean checkIfExists(InscriptionId id) {
        return repository.existsById(id);
    }
}