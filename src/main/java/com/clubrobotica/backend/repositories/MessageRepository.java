package com.clubrobotica.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clubrobotica.backend.models.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{
    Message findByConversationIdConversation(Integer idConversation);
}
