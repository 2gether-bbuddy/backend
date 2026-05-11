package com.clubrobotica.backend.repositories;

import com.clubrobotica.backend.models.Career;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerRepository extends JpaRepository<Career, Integer>{
    
}
