package edu.self.sams.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class UserEntity {
    @Id
    @Column(name="user_id", nullable=false, length=50)
    private String userId;
    @Column(name="password",  nullable=false, length=50)
    private String password;
    @Column(name="full_name",   nullable=false, length=50)
    private String fullName;
    @Column(name="role", nullable = false, length=50)
    private String role;
    @Column(name="email", nullable = false, length=50)
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
