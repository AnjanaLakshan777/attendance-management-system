package edu.self.sams.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EnrollmentId implements Serializable {
    private String courseCode;
    private String regNo;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentId that = (EnrollmentId) o;
        return Objects.equals(courseCode, that.courseCode) && Objects.equals(regNo, that.regNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode, regNo);
    }


    public EnrollmentId() {
    }

    public EnrollmentId(String courseCode, String regNo) {
        this.courseCode = courseCode;
        this.regNo = regNo;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
}
