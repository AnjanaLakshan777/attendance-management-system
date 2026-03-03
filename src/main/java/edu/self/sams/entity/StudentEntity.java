package edu.self.sams.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="student")
public class StudentEntity {
    @Id
    @Column(name="reg_no", length=50)
    private String regNo;
    @Column(name="name", nullable=false, length=50)
    private String name;
    @Column(name="email", nullable=false, length=50)
    private String email;
    @Column(name="tele_no", nullable=false, length=50)
    private String teleNo;

    @OneToMany(mappedBy = "student")
    private List<EnrollmentEntity> enrollments = new ArrayList<>();

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

    public List<EnrollmentEntity> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<EnrollmentEntity> enrollments) {
        this.enrollments = enrollments;
    }
}
