package com.clubrobotica.backend.models;

import jakarta.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String content;
    private String sender;
    
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
    
    public String getSender(){
        return sender;
    }
    
    public void setSender(String sender){
        this.sender = sender;
    }
}
