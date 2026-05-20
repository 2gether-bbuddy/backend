package com.clubrobotica.backend.services;

import com.clubrobotica.backend.models.Message;
import com.clubrobotica.backend.models.ParticipantId;
import com.clubrobotica.backend.models.Participant_Conversation;
import com.clubrobotica.backend.repositories.MessageRepository;
import com.clubrobotica.backend.repositories.ParticipantConversationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final ParticipantConversationRepository participantRepository; // <-- Inyectamos TU repositorio

    public MessageService(MessageRepository messageRepository, ParticipantConversationRepository participantRepository) {
        this.messageRepository = messageRepository;
        this.participantRepository = participantRepository;
    }

    public Message saveMessage(Message message) {
        // 1. Ponemos la fecha exacta del servidor
        message.setDateSend(LocalDateTime.now());

        // ================================================================
        // 2. MAGIA: Registro automático de participantes en la conversación
        // ================================================================
        if (message.getSender() != null && message.getConversation() != null) {

            String matricula = message.getSender().getControlNumber();

            // OJO: Verifica que en tu modelo Conversation el getter se llame getIdConversation()
            // Si le pusiste solo getId(), cámbialo aquí abajo.
            Integer idConversacion = message.getConversation().getIdConversation();

            if (matricula != null && idConversacion != null) {
                // Armamos la llave compuesta para buscarlo
                ParticipantId pid = new ParticipantId();
                pid.setControlNumber(matricula);
                pid.setIdConversation(idConversacion);

                // Si este alumno NO está registrado en este chat, lo guardamos
                if (!participantRepository.existsById(pid)) {
                    Participant_Conversation nuevoParticipante = new Participant_Conversation();
                    nuevoParticipante.setId(pid);
                    nuevoParticipante.setUsuario(message.getSender());
                    nuevoParticipante.setConversacion(message.getConversation());
                    nuevoParticipante.setEntry_date(LocalDateTime.now());

                    participantRepository.save(nuevoParticipante);
                }
            }
        }
        // ================================================================

        // 3. Finalmente, guardamos el mensaje en la BD
        return messageRepository.save(message);
    }

    public List<Message> getHistory(Integer conversationId) {
        return messageRepository.findByConversation_IdConversationOrderByDateSendAsc(conversationId);
    }
}