package com.clubrobotica.backend.dto;

public class LoginRequest {
    private String controlNumber;
    private String password;
    
    public LoginRequest(){
        
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
