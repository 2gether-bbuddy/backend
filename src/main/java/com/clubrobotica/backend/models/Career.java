package com.clubrobotica.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "carreras")
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCareer;
    
    private String nameCareer;
    
    public Career() {}

    public Integer getIdCareer() {
        return idCareer;
    }

    public void setIdCareer(Integer idCareer) {
        this.idCareer = idCareer;
    }

    public String getNameCareer() {
        return nameCareer;
    }

    public void setNameCareer(String nameCareer) {
        this.nameCareer = nameCareer;
    }
}
