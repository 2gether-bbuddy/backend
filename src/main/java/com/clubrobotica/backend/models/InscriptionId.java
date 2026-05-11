package com.clubrobotica.backend.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InscriptionId implements Serializable {
    private Integer idPublication;
    private String controlNum;
    
    public InscriptionId(){
    }
    
    public Integer getIdPublication(){
        return idPublication;
    }
    public void setIdPublication(Integer idPublication){
        this.idPublication = idPublication;
    }
    
    public String getControlNum(){
        return controlNum;
    }
    public void setControlNum(String controlNum){
        this.controlNum = controlNum;
    }
    
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof InscriptionId)) return false;
        InscriptionId that = (InscriptionId) o;
        return Objects.equals(idPublication, that.idPublication) && Objects.equals(controlNum, that.controlNum);
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(idPublication, controlNum);
    }
}

