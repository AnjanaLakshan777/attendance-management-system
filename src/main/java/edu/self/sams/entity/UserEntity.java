package edu.self.sams.entity;

import jakarta.persistence.*;

@Entity
@Table(name="user")
public class UserEntity {
    @Id
    @Column(name="user_id", length=50)
    private String userId;
    @Column(name="password",  nullable=false, length=50)
    private String password;
    @Column(name="role", nullable = false, length=50)
    private String role;

    @OneToOne(mappedBy = "userEntity")
    private LecturerEntity lecturerEntity;

    public UserEntity() {
    }

    public UserEntity(String userId, String password, String role) {
        this.userId = userId;
        this.password = password;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
