package com.example.timemanagementapp.dto.response;

public class JwtReponseDTO {
    private String jwt;
    private String username;
    private final String type = "Bearer";

    public String getType() {
        return type;
    }

    public JwtReponseDTO(String jwt, String username) {
        this.jwt = jwt;
        this.username = username;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
