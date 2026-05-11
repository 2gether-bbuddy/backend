package com.clubrobotica.backend.repositories;

import com.clubrobotica.backend.models.AcademicHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicHistoryRepository extends JpaRepository<AcademicHistory, Integer>{
    AcademicHistory findByUser(String control_number);
}
