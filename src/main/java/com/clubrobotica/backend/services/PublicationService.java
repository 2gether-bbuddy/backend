package com.clubrobotica.backend.services;

import com.clubrobotica.backend.models.Publication;
import com.clubrobotica.backend.repositories.PublicationRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PublicationService {
    private final PublicationRepository publicationRepository;
    
    public PublicationService(PublicationRepository publicationRepository){
        this.publicationRepository = publicationRepository;
    }
    
    // Obtener todos
    public List<Publication> getPublication(){
        return publicationRepository.findAll();
    }
    
    public Publication savePublication(Publication publication){
        publication.setDateCreation(LocalDateTime.now());
        return publicationRepository.save(publication);
    }
    
    public Publication getType(String type){
        return publicationRepository.findByType(type);
    }
}
