package com.clubrobotica.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "solicitud")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idApplication;
    
    private String reason;
    private String skills;
    private String projects;
    private String state;
    
    @ManyToOne
    @JoinColumn(name = "num_control")
    private User user;
    
    public Application(){
    }
    public Integer getIdApplication(){
        return idApplication;
    }
    public void setApplication(Integer idApplication){
        this.idApplication = idApplication;
    }
    public String getReason(){
        return reason;
    }
    public void setReason(String reason){
        this.reason = reason;
    }
    public String getSkills(){
        return skills;
    }
    public void setSkills(String skills){
        this.skills = skills;
    }
    public String getProjects(){
        return projects;
    }
    public void setProjects(String projects){
        this.projects = projects;
    }
    public String getState(){
        return state;
    }
    public void setState(String state){
        this.state = state;
    }
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }
}
