package com.clubrobotica.backend.repositories;

import com.clubrobotica.backend.models.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester, Integer>{
    
}
