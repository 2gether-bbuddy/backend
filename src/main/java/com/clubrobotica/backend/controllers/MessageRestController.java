package com.clubrobotica.backend.controllers;

import com.clubrobotica.backend.models.Message;
import com.clubrobotica.backend.services.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@CrossOrigin("*")
public class MessageRestController {

    private final MessageService messageService;

    public MessageRestController(MessageService messageService) {
        this.messageService = messageService;
    }

    // Ruta HTTP normal para pedir el historial: /messages/history/1
    @GetMapping("/history/{conversationId}")
    public List<Message> getHistory(@PathVariable Integer conversationId) {
        return messageService.getHistory(conversationId);
    }
}