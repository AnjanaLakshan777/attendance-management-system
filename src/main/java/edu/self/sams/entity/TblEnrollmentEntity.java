package edu.self.sams.entity;


public class TblEnrollmentEntity {

    private String regNo;
    private String studentName;
    private String courseCode;
    private String courseName;
    private int batch;
    private String status;

    public TblEnrollmentEntity() {
    }

    public TblEnrollmentEntity(String regNo, String studentName, String courseCode, String courseName, int batch, String status) {
        this.regNo = regNo;
        this.studentName = studentName;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.batch = batch;
        this.status = status;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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
}
