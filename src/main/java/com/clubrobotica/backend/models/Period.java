package com.clubrobotica.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "periodos")
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPeriod;
    private String namePeriod;
    
    public Period(){
        
    }

    public Integer getId_period() {
        return idPeriod;
    }

    public void setId_period(Integer id_period) {
        this.idPeriod = id_period;
    }

    public String getName_period() {
        return namePeriod;
    }

    public void setName_period(String name_period) {
        this.namePeriod = name_period;
    }
}
