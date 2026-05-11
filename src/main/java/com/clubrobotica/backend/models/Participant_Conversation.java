package com.clubrobotica.backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "participante_conversacion")
public class Participant_Conversation {
    @EmbeddedId
    private ParticipantId id;
    private LocalDateTime entry_date;

    public LocalDateTime getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(LocalDateTime entry_date) {
        this.entry_date = entry_date;
    }
    
    public Participant_Conversation(){
        
    }
    
    public ParticipantId getId(){
        return id;
    }
    public void setId(ParticipantId id){
        this.id = id;
    }
}