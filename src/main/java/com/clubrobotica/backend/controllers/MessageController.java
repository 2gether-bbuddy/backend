package com.clubrobotica.backend.controllers;

import com.clubrobotica.backend.models.Conversation;
import com.clubrobotica.backend.models.Message;
import com.clubrobotica.backend.services.MessageService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // El celular envía el mensaje a: /app/chat/{idDelCanal}
    @MessageMapping("/chat/{conversationId}")

    // El servidor lo retransmite a: /topic/conversation/{idDelCanal}
    @SendTo("/topic/conversation/{conversationId}")
    public Message sendMessage(@DestinationVariable Integer conversationId, Message message) {

        // 1. Vinculamos el mensaje al canal correcto
        Conversation conv = new Conversation();
        conv.setIdConversation(conversationId);
        message.setConversation(conv);

        // 2. Lo guardamos en la base de datos
        // 3. El return hace que Spring dispare el mensaje por el WebSocket automáticamente
        return messageService.saveMessage(message);
    }
}