package com.clubrobotica.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "semestre")
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSemester;
    
    private String nameSemester;
    
    public Semester() {
    }

    public Integer getIdSemester() {
        return idSemester;
    }

    public void setIdSemester(Integer idSemester) {
        this.idSemester = idSemester;
    }

    public String getNameSemester() {
        return nameSemester;
    }

    public void setNameSemester(String nameSemester) {
        this.nameSemester = nameSemester;
    }
}
