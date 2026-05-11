package com.clubrobotica.backend.services;

import com.clubrobotica.backend.models.Message;
import com.clubrobotica.backend.repositories.MessageRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    
    public MessageService(
        MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }
    
    public Message save(Message message){
        message.setDateSend(LocalDateTime.now());
        
        return messageRepository.save(message);
    }
    
    public List<Message> getAll(){
        return messageRepository.findAll();
    }
}
