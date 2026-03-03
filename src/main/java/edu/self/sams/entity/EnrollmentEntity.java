package edu.self.sams.entity;

import jakarta.persistence.*;

@Entity
@Table(name="enrollment")
public class EnrollmentEntity {
    @EmbeddedId
    private EnrollmentId enrollmentId;

    @ManyToOne
    @MapsId("courseCode")
    @JoinColumn(name="course_code")
    private CourseEntity course;

    @ManyToOne
    @MapsId("regNo")
    @JoinColumn(name="reg_no")
    private StudentEntity student;

    @Column(name="batch", nullable=false, length=50)
    private int batch;
    @Column(name="status", nullable=false, length=50)
    private String status;

    public EnrollmentEntity() {
    }

    public EnrollmentEntity(EnrollmentId enrollmentId, CourseEntity course, StudentEntity student, int batch, String status) {
        this.enrollmentId = enrollmentId;
        this.course = course;
        this.student = student;
        this.batch = batch;
        this.status = status;
    }

    public EnrollmentId getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(EnrollmentId enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
