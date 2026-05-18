package com.clubrobotica.backend.services;

import com.clubrobotica.backend.models.Message;
import com.clubrobotica.backend.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message saveMessage(Message message) {
        message.setDateSend(LocalDateTime.now());
        return messageRepository.save(message);

    }

    // Agrega este método debajo del que ya tienes para guardar mensajes
    public List<Message> getHistory(Integer conversationId) {
        return messageRepository.findByConversation_IdConversationOrderByDateSendAsc(conversationId);
    }
}