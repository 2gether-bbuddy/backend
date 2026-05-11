package com.clubrobotica.backend.repositories;

import com.clubrobotica.backend.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Integer>{
    Application findByState(String state);
}
