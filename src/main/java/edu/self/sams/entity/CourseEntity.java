package edu.self.sams.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="course")
public class CourseEntity {
    @Id
    @Column(name="course_code", nullable=false, length=50)
    private String courseCode;
    @Column(name="course_name", nullable=false, length=50)
    private String courseName;
    @Column(name="duration", nullable=false, length=50)
    private String duration;

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
}
