package edu.self.sams.dto;

public class CourseSubjectDto {
    private String courseCode;
    private String courseName;
    private String subjectCode;
    private String subjectName;

    public CourseSubjectDto() {
    }

    public CourseSubjectDto(String courseCode, String courseName, String subjectCode, String subjectName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
