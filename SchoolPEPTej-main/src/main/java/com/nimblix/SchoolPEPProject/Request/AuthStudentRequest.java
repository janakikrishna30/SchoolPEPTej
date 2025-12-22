package com.nimblix.SchoolPEPProject.Request;

public class AuthStudentRequest {

    private String email;
    private String password;

    // ✅ REQUIRED GETTERS
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // ✅ REQUIRED SETTERS
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
