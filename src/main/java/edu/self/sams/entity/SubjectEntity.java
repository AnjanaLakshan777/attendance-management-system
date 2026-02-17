package edu.self.sams.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="subject")
public class SubjectEntity {
    @Id
    @Column(name="subject_code",length=25)
    private String subjectCode;
    @Column(name="subject_name", nullable=false, length=50)
    private String subjectName;

    @ManyToMany(mappedBy="subjects")
    private Set<CourseEntity> courses = new HashSet<>();

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

    public Set<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseEntity> courses) {
        this.courses = courses;
    }
}
