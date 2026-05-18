package com.clubrobotica.backend.services;

import com.clubrobotica.backend.models.Conversation;
import com.clubrobotica.backend.repositories.ConversationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConversationService {

    private final ConversationRepository repository;

    public ConversationService(ConversationRepository repository) {
        this.repository = repository;
    }

    // Método para devolver todos los canales (Para el GET)
    public List<Conversation> getAllConversations() {
        return repository.findAll();
    }

    // Método para guardar un canal nuevo (Para el POST)
    public Conversation saveConversation(Conversation conversation) {
        // Le ponemos la marca de tiempo exacta del servidor
        conversation.setDate_creation(LocalDateTime.now());
        return repository.save(conversation);
    }
}