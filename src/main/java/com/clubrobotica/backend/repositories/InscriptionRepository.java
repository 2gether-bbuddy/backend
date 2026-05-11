package com.clubrobotica.backend.repositories;

import com.clubrobotica.backend.models.InscriptionId;
import com.clubrobotica.backend.models.InscriptionPublication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscriptionRepository extends JpaRepository<InscriptionPublication, InscriptionId>{
    
}
