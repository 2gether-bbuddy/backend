package com.clubrobotica.backend.services;

import com.clubrobotica.backend.models.Publication;
import com.clubrobotica.backend.models.User;
import com.clubrobotica.backend.repositories.PublicationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PublicationService {

    private final PublicationRepository publicationRepository;

    public PublicationService(PublicationRepository publicationRepository){
        this.publicationRepository = publicationRepository;
    }

    public List<Publication> getPublication(){
        return publicationRepository.findAll();
    }

    public Publication savePublication(Publication publication){
        publication.setDateCreation(LocalDateTime.now());
        return publicationRepository.save(publication);
    }

    public List<Publication> getType(String type){
        return publicationRepository.findByType(type);
    }

    public boolean archivePublication(Integer idPublication) {
        Optional<Publication> optionalPub = publicationRepository.findById(idPublication);
        if (optionalPub.isPresent()) {
            Publication pub = optionalPub.get();
            pub.setType("ARCHIVADO");
            publicationRepository.save(pub);
            return true;
        }
        return false;
    }

    // NUEVO MÉTODO: Actualiza una publicación existente
    public Publication updatePublication(Integer id, Publication details) {
        Publication pub = publicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        pub.setTittle(details.getTittle());
        pub.setDescription(details.getDescription());
        pub.setEventDate(details.getEventDate());
        pub.setDoInscription(details.getDoInscription());
        pub.setCapacity(details.getCapacity());

        return publicationRepository.save(pub);
    }

    // NUEVO MÉTODO: Obtiene la lista de alumnos inscritos
    public List<User> getParticipants(Integer idPublication) {
        return publicationRepository.findParticipantsByPublicationId(idPublication);
    }
}