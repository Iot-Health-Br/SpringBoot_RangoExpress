package com.SpringBoot_RangoExpress.DTO;

import java.util.List;

public class LoginResponse {
    private boolean authenticated;
    private String message;
    private String username;
    private List<String> roles;
    private Long userId;      // Novo campo
    private String fullName;  // Novo campo
    private String endereco;
    private String latitude;
    private String longitude;

    public LoginResponse(boolean authenticated, String message, String username, List<String> roles, Long userId, String fullName, String endereco, String latitude, String longitude) {
        this.authenticated = authenticated;
        this.message = message;
        this.username = username;
        this.roles = roles;
        this.userId = userId;
        this.fullName = fullName;
        this.endereco = endereco;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters e Setters
    public boolean isAuthenticated() { return authenticated; }
    public void setAuthenticated(boolean authenticated) { this.authenticated = authenticated; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "authenticated=" + authenticated +
                ", message='" + message + '\'' +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                ", userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", endereco='" + endereco + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
