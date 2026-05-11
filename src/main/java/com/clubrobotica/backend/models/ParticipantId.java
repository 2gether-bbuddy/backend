package com.clubrobotica.backend.models;

import java.io.Serializable;
import java.util.Objects;

public class ParticipantId implements Serializable{
    private Integer idConversation;
    private String contolNumber;
    
    public ParticipantId(){
    }
    
    public Integer getIdConversation(){
        return idConversation;
    }
    public void setIdConversation(Integer idConversation){
        this.idConversation = idConversation;
    }
    public String getControlNumber(){
        return contolNumber;
    }
    public void setControlNumber(String controlNumber){
        this.contolNumber = controlNumber;
    }
    
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof ParticipantId)) return false;
        ParticipantId that = (ParticipantId) o;
        return Objects.equals(idConversation, that.idConversation) && Objects.equals(contolNumber, that.contolNumber);
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(idConversation, contolNumber);
    }
    
}
