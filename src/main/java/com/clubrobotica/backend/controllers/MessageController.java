package com.clubrobotica.backend.controllers;

import com.clubrobotica.backend.models.Conversation;
import com.clubrobotica.backend.models.Message;
import com.clubrobotica.backend.models.User;
import com.clubrobotica.backend.services.MessageService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // OJO AQUÍ: Recibimos un Map (JSON plano) en lugar del objeto Message directamente
    @MessageMapping("/chat/{conversationId}")
    @SendTo("/topic/conversation/{conversationId}")
    public Message sendMessage(@DestinationVariable Integer conversationId, Map<String, String> payload) {

        // 1. Extraemos los datos que envía React Native
        String texto = payload.get("content");
        String matricula = payload.get("senderId"); // <-- React Native DEBE mandar este campo

        // 2. Armamos el mensaje desde cero
        Message mensaje = new Message();
        mensaje.setContent(texto);

        // 3. Vinculamos el canal (Esto ya lo tenías)
        Conversation conv = new Conversation();
        conv.setIdConversation(conversationId);
        mensaje.setConversation(conv);

        // 4. ¡NUEVO! Vinculamos al alumno que lo envió
        if (matricula != null) {
            User usuarioEmisor = new User();
            usuarioEmisor.setControlNumber(matricula); // Construimos el "cascarón"
            mensaje.setSender(usuarioEmisor);
        }

        // 5. Lo mandamos al servicio (Aquí ya pasará la validación y llenará la tabla)
        return messageService.saveMessage(mensaje);
    }
}