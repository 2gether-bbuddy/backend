package com.clubrobotica.backend.controllers;

import com.clubrobotica.backend.models.Conversation;
import com.clubrobotica.backend.services.ConversationService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conversation")
@CrossOrigin("*")
public class ConversationController {
    private final ConversationService conversationService;
    public ConversationController(
        ConversationService conversationService){
        this.conversationService = conversationService;
    }
    
    @GetMapping
    public List<Conversation> getAll(){
        return conversationService.getAll();
    }
    
    @PostMapping
    public Conversation save(
        @RequestBody Conversation conversation){
        return conversationService.save(conversation);
    }
}
