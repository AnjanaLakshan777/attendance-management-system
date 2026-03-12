package edu.self.sams.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="schedule_class")
public class ScheduleClassEntity {
    @Id
    @Column(name="class_id",length = 50)
    private String classId;
    @Column(name="date",nullable = false,length = 50)
    private String date;
    @Column(name="start_time",nullable = false,length = 50)
    private String startTime;
    @Column(name="end_time",nullable = false,length = 50)
    private String endTime;
    @Column(name="batch",nullable = false,length = 50)
    private int batch;
    @Column(name="status",nullable = false,length = 50)
    private String status;

    @ManyToOne
    private CourseEntity course;
    @ManyToOne
    private SubjectEntity subject;
    @ManyToOne
    private LecturerEntity lecturer;

    @OneToMany(mappedBy = "scheduleClass")
    private List<AttendanceEntity> attendances = new ArrayList<>();

    public ScheduleClassEntity() {
    }

    public ScheduleClassEntity(String classId, String date, String startTime, String endTime, int batch, String status, CourseEntity course, SubjectEntity subject, LecturerEntity lecturer) {
        this.classId = classId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.batch = batch;
        this.status = status;
        this.course = course;
        this.subject = subject;
        this.lecturer = lecturer;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public SubjectEntity getSubject() {
        return subject;
    }

    public void setSubject(SubjectEntity subject) {
        this.subject = subject;
    }

    public LecturerEntity getLecturer() {
        return lecturer;
    }

    public void setLecturer(LecturerEntity lecturer) {
        this.lecturer = lecturer;
    }

    public List<AttendanceEntity> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<AttendanceEntity> attendances) {
        this.attendances = attendances;
    }
}
