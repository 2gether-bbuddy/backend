package com.clubrobotica.backend.repositories;

import com.clubrobotica.backend.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    // Spring Boot genera la consulta SQL automáticamente basándose en el nombre de este método
    List<Message> findByConversation_IdConversationOrderByDateSendAsc(Integer idConversation);
}