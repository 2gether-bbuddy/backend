package com.clubrobotica.backend.dto;

import com.clubrobotica.backend.models.Career;

public class RegisterRequest {
    private String controlNumber;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String role;
    private Career career;

    // NUEVOS CAMPOS PARA LA SOLICITUD
    private String skills;
    private String reason;
    private String projects;

    // Getters y Setters
    public String getControlNumber() { return controlNumber; }
    public void setControlNumber(String controlNumber) { this.controlNumber = controlNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Career getCareer() { return career; }
    public void setCareer(Career career) { this.career = career; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getProjects() { return projects; }
    public void setProjects(String projects) { this.projects = projects; }
}