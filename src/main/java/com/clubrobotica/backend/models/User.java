package com.clubrobotica.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class User {
    @Id
    @Column(name = "control_number")
    private String controlNumber;
    
    private String name;
    private String email;
    private String password;
    private String phone;
    
    @ManyToOne
    @JoinColumn(name = "id_carrera")
    private Career career;
    
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
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public Career getCareer(){
        return career;
    }
    
    public void setCareer(Career career){
        this.career = career;
    }
    
    public String getControlNumber(){
        return controlNumber;
    }
    
    public void setControlNumber(String control_number){
        this.controlNumber = control_number;
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
