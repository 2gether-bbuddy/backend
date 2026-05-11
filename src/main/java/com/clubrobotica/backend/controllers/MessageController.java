package com.clubrobotica.backend.controllers;

import com.clubrobotica.backend.models.Message;
import com.clubrobotica.backend.services.MessageService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@CrossOrigin("*")
public class MessageController {
    private final MessageService messageService;
    public MessageController(
        MessageService messageService){
        this.messageService = messageService;
    }
    
    @GetMapping
    public List<Message> getAll(){
        return messageService.getAll();
    }
    
    @PostMapping
    public Message save(
        @RequestBody Message message){
        return messageService.save(message);
    }
}
