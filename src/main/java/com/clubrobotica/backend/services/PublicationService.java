package com.clubrobotica.backend.services;

import com.clubrobotica.backend.models.Publication;
import com.clubrobotica.backend.repositories.PublicationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        // El servidor pone su propia marca de tiempo automática
        publication.setDateCreation(LocalDateTime.now());
        // El eventDate ya viene dentro del objeto 'publication', así que se guarda intacto
        return publicationRepository.save(publication);
    }

    // CORRECCIÓN: Ahora concuerda con el repositorio devolviendo una List
    public List<Publication> getType(String type){
        return publicationRepository.findByType(type);
    }
}