package com.clubrobotica.backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inscripciones_publicacion")
public class InscriptionPublication {
    @EmbeddedId
    private InscriptionId id;
    private LocalDateTime dateInscription;
    private boolean physical_assistance;
    public InscriptionPublication(){
    }
    public InscriptionId getId(){
        return id;
    }
    public void setId(InscriptionId id){
        this.id = id;
    }
    public LocalDateTime getDateInscription(){
        return dateInscription;
    }
    public void setDateInscription(LocalDateTime dateInscription){
        this.dateInscription = dateInscription;
    }
    public boolean getAssistance(){
        return physical_assistance;
    }
    public void setAssistance (boolean physical_assistance){
        this.physical_assistance = physical_assistance;
    }
}
