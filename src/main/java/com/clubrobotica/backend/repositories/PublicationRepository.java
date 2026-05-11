package com.clubrobotica.backend.repositories;

import com.clubrobotica.backend.models.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<Publication, Integer>{
    Publication findByType(String type);
}
