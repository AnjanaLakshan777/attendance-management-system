package edu.self.sams.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AttendanceId implements Serializable {
    private String classId;
    private String regNo;

    public AttendanceId() {
    }

    public AttendanceId(String classId, String regNo) {
        this.classId = classId;
        this.regNo = regNo;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceId that = (AttendanceId) o;
        return Objects.equals(classId, that.classId) && Objects.equals(regNo, that.regNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, regNo);
    }
}
