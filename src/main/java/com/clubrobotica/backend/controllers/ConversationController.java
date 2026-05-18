package com.clubrobotica.backend.controllers;

import com.clubrobotica.backend.models.Conversation;
import com.clubrobotica.backend.services.ConversationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversations") // <-- ESTO MATA EL ERROR 404
@CrossOrigin("*")
public class ConversationController {

    private final ConversationService service;

    public ConversationController(ConversationService service) {
        this.service = service;
    }

    // Atiende la recarga de la lista de canales
    @GetMapping
    public List<Conversation> getAll() {
        return service.getAllConversations();
    }

    // Atiende la creación de un nuevo canal
    @PostMapping
    public Conversation create(@RequestBody Conversation conversation) {
        return service.saveConversation(conversation);
    }
}