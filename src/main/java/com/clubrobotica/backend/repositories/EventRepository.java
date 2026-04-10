package com.clubrobotica.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clubrobotica.backend.models.Event;

public interface EventRepository extends JpaRepository<Event, Long>{
    
}
