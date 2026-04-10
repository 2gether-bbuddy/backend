package com.clubrobotica.backend.models;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String career;
    private String semester;
    private String control_number;
    private String email;
    private String password;
    private String phone;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    public enum Role {
        PRESIDENTE,
        MESA,
        MIEMBRO,
        ALUMNO
    }
    
    public User(){      
    }
    
    public Long getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getCareer(){
        return career;
    }
    
    public void setCareer(String career){
        this.career = career;
    }
    
    public String getSemester(){
        return semester;
    }
    
    public void setSemester(String semester){
        this.semester = semester;
    }
    
    public String getControlNumber(){
        return control_number;
    }
    
    public void setControlNumber(String control_number){
        this.control_number = control_number;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getPhone(){
        return phone;
    }
    
    public void setPhone(String phone){
        this.phone = phone;
    }
    
    public Role getRole(){
        return role;
    }
    
    public void setRole(Role role){
        this.role = role;
    }
}
