package edu.self.sams.entity;

import jakarta.persistence.*;

@Entity
@Table(name="lecturer")
public class LecturerEntity {
    @Id
    @Column(name="user_id", length=50)
    private String userId;
    @Column(name="name", nullable=false, length=50)
    private String name;
    @Column(name="email", nullable=false, length=50)
    private String email;
    @Column(name="tele_no", nullable=false, length=50)
    private String teleNo;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name="user_id")
    private UserEntity userEntity;

    public LecturerEntity() {
    }

    public LecturerEntity(String userId, String name, String email, String teleNo, UserEntity userEntity) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.teleNo = teleNo;
        this.userEntity = userEntity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTeleNo() {
        return teleNo;
    }

    public void setTeleNo(String teleNo) {
        this.teleNo = teleNo;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
