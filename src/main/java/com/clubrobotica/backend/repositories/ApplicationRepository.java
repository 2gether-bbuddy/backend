package com.clubrobotica.backend.repositories;

import com.clubrobotica.backend.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    // Este método mágico le dice a Spring que busque por la columna "state"
    List<Application> findByState(String state);
}