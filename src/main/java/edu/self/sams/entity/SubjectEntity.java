package edu.self.sams.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="subject")
public class SubjectEntity {
    @Id
    @Column(name="subject_code",length=50)
    private String subjectCode;
    @Column(name="subject_name", nullable=false, length=50)
    private String subjectName;

    //Course and Subject
    @ManyToMany(mappedBy="subjects")
    private List<CourseEntity> courses = new ArrayList<>();

    //Subject and Lecturer
    @ManyToMany
    @JoinTable(
            name = "subject_lecturer",
            joinColumns = @JoinColumn(name="subject_code"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    private List<LecturerEntity> lecturers = new ArrayList<>();

    //Subject and schedule class
    @OneToMany(mappedBy = "subject")
    private List<ScheduleClassEntity> scheduleClasses = new ArrayList<>();

    public SubjectEntity() {
    }

    public SubjectEntity(String subjectCode, String subjectName) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseEntity> courses) {
        this.courses = courses;
    }

    public List<LecturerEntity> getLecturers() {
        return lecturers;
    }

    public List<ScheduleClassEntity> getScheduleClasses() {
        return scheduleClasses;
    }

    public void setScheduleClasses(ArrayList<ScheduleClassEntity> scheduleClasses) {
        this.scheduleClasses = scheduleClasses;
    }

    public void setLecturers(List<LecturerEntity> lecturers) {
        this.lecturers = lecturers;
    }
}
