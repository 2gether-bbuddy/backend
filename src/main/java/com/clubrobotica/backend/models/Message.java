package com.clubrobotica.backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mensajes")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String content;
    private LocalDateTime dateSend;

    @ManyToOne
    @JoinColumn(name = "id_conversacion")
    private Conversation conversation;
    
    @ManyToOne
    @JoinColumn(name = "num_control_emisor")
    private User sender;
    
    public Message(){
    }
    
    public Long getId(){
        return id;
    }
    
    public String getContent(){
        return content;
    }
    
    public void setContent(String content){
        this.content = content;
    }
    
    public User getSender(){
        return sender;
    }
    
    public void setSender(User sender){
        this.sender = sender;
    }
    public LocalDateTime getDateSend(){
        return dateSend;
    }
    public void setDateSend(LocalDateTime dateSend){
        this.dateSend = dateSend;
    }
    public Conversation getConversation(){
        return conversation;
    }
    public void setState(Conversation conversation){
        this.conversation = conversation;
    }
}
