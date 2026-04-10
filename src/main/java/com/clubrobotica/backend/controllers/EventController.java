package com.clubrobotica.backend.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.clubrobotica.backend.models.Event;
import com.clubrobotica.backend.services.EventService;

@RestController
@RequestMapping("/events")

public class EventController {
    @Autowired
    private EventService service;
    
    @GetMapping
    public List<Event> getEvents(){
        return service.getAll();
    }
}
