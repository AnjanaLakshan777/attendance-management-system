package edu.self.sams.dto;

public class CourseDto {
    private String courseCode;
    private String courseName;
    private String duration;

    public CourseDto() {}

    public CourseDto(String courseCode, String courseName, String duration) {
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
