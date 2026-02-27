package edu.self.sams.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="course")
public class CourseEntity {
    @Id
    @Column(name="course_code", length=50)
    private String courseCode;
    @Column(name="course_name", nullable=false, length=50)
    private String courseName;
    @Column(name="duration", nullable=false, length=50)
    private String duration;
    @ManyToMany
    @JoinTable(
            name = "course_subject",
            joinColumns = @JoinColumn(name="course_code"),
            inverseJoinColumns = @JoinColumn(name="subject_code")
    )
    private List<SubjectEntity> subjects = new ArrayList<>();

    public CourseEntity() {
    }

    public CourseEntity(String courseCode, String courseName, String duration) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.duration = duration;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<SubjectEntity> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectEntity> subjects) {
        this.subjects = subjects;
    }
}
