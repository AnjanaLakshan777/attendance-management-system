package edu.self.sams.entity;

import jakarta.persistence.*;


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

    @ManyToOne
    private CourseEntity course;
    @ManyToOne
    private SubjectEntity subject;
    @ManyToOne
    private LecturerEntity lecturer;

    public ScheduleClassEntity() {
    }

    public ScheduleClassEntity(String classId, String date, String startTime, String endTime, CourseEntity course, SubjectEntity subject, LecturerEntity lecturer) {
        this.classId = classId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
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
}
