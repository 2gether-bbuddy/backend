package com.clubrobotica.backend.repositories;

import com.clubrobotica.backend.models.InscriptionPublication;
import com.clubrobotica.backend.models.InscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionRepository extends JpaRepository<InscriptionPublication, InscriptionId> {
    // JpaRepository ya trae el existsById()
}