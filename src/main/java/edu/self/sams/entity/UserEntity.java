package edu.self.sams.entity;

public class UserEntity {
    private String userId;
    private String password;
    private String fullName;
    private String role;
    private String email;

    public UserEntity() {
    }

    public UserEntity(String userId, String password, String fullName, String role, String email) {
        this.userId = userId;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
