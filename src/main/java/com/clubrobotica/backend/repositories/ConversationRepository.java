package com.clubrobotica.backend.repositories;

import com.clubrobotica.backend.models.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Integer>{
    
}
