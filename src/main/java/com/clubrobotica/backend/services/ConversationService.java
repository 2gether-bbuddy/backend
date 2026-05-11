package com.clubrobotica.backend.services;

import com.clubrobotica.backend.models.Conversation;
import com.clubrobotica.backend.repositories.ConversationRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;
    
    public ConversationService(
        ConversationRepository conversationRepository){
        this.conversationRepository = conversationRepository;
    }
    
    public Conversation save(
        Conversation conversation){
        conversation.setDate_creation(LocalDateTime.now());
        
        return conversationRepository.save(conversation);
    }
    
    public List<Conversation> getAll(){
        return conversationRepository.findAll();
    }
}
