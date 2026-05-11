package com.clubrobotica.backend.repositories;

import com.clubrobotica.backend.models.ParticipantId;
import com.clubrobotica.backend.models.Participant_Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantConversationRepository extends JpaRepository<Participant_Conversation, ParticipantId>{
    
}
