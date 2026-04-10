package com.clubrobotica.backend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clubrobotica.backend.models.Event;
import com.clubrobotica.backend.repositories.EventRepository;

@Service
public class EventService {
    @Autowired
    private EventRepository repo;
    
    public List<Event> getAll(){
        return repo.findAll();
    }
    
    public Event save(Event e){
        return repo.save(e);
    }
}
