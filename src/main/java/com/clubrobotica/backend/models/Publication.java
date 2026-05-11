package com.clubrobotica.backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "publicaciones")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPublication;
    
    private String tittle;
    private String description;
    private String type;
    private Boolean DoInscription;
    private Integer capacity;
    
    private LocalDateTime dateCreation;
    
    @ManyToOne
    @JoinColumn(name = "num_control_autor")
    private User author;
    
    public Publication(){
        
    }
    public String getTittle(){
        return tittle;
    }
    public void setTittle(String tittle){
        this.tittle = tittle;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }
    public Boolean getDoInscription(){
        return DoInscription;
    }
    public void setDescription(Boolean DoInscription){
        this.DoInscription = DoInscription;
    }
    public Integer getCapacity(){
        return capacity;
    }
    public void setCapacity(Integer capacity){
        this.capacity = capacity;
    }
    public LocalDateTime getDateCreation(){
        return dateCreation;
    }
    public void setDateCreation(LocalDateTime dateCreation){
        this.dateCreation = dateCreation;
    }
    public User getAuthor(){
        return author;
    }
    public void setAuthor(User author){
        this.author = author;
    }
    public Integer getIdPublication(){
        return idPublication;
    }
    public void setIdPublication(Integer idPublication){
        this.idPublication = idPublication;
    }
}
