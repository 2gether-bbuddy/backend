package com.clubrobotica.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Historial_Academico")
public class AcademicHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_history;
    
    @ManyToOne
    @JoinColumn(name = "control_number")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "id_semestre")
    private Semester semester;
    
    @ManyToOne
    @JoinColumn(name = "id_Periodo")
    private Period period;
    
    private boolean current;
    
    public AcademicHistory(){
        
    }

    public Integer getId_history() {
        return id_history;
    }

    public void setId_history(Integer id_history) {
        this.id_history = id_history;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }
}
