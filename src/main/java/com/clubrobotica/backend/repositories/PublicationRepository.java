package com.clubrobotica.backend.repositories;

import com.clubrobotica.backend.models.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Integer> {
    // CORRECCIÓN: Devuelve una lista completa de ese tipo, no solo uno
    List<Publication> findByType(String type);
}