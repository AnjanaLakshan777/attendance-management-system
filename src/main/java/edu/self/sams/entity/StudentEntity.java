package edu.self.sams.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="student")
public class StudentEntity {
    @Id
    @Column(name="reg_no")
    private String regNo;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="tele_no")
    private String teleNo;

    public StudentEntity() {
    }
    public StudentEntity(String regNo, String name, String email, String teleNo) {
        this.regNo = regNo;
        this.name = name;
        this.email = email;
        this.teleNo = teleNo;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
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
}
