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

    @ManyToMany(mappedBy="subjects")
    private List<CourseEntity> courses = new ArrayList<>();

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
}
