package edu.self.sams.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "attendance")
public class AttendanceEntity {
    @EmbeddedId
    private AttendanceId id;

    @ManyToOne
    @MapsId("classId")
    @JoinColumn(name = "class_id")
    private ScheduleClassEntity scheduleClass;

    @ManyToOne
    @MapsId("regNo")
    @JoinColumn(name = "reg_no")
    private StudentEntity student;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "remark", length = 255)
    private String remark;

    public AttendanceEntity() {
    }

    public AttendanceEntity(AttendanceId id, ScheduleClassEntity scheduleClass, StudentEntity student, String status, String remark) {
        this.id = id;
        this.scheduleClass = scheduleClass;
        this.student = student;
        this.status = status;
        this.remark = remark;
    }

    public AttendanceId getId() {
        return id;
    }

    public void setId(AttendanceId id) {
        this.id = id;
    }

    public ScheduleClassEntity getScheduleClass() {
        return scheduleClass;
    }

    public void setScheduleClass(ScheduleClassEntity scheduleClass) {
        this.scheduleClass = scheduleClass;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
