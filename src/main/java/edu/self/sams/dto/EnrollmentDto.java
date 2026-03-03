package edu.self.sams.dto;

public class EnrollmentDto {
    private String courseCode;
    private String regNo;
    private int batch;
    private String status;

    public EnrollmentDto() {
    }

    public EnrollmentDto(String courseCode, String regNo, int batch, String status) {
        this.courseCode = courseCode;
        this.regNo = regNo;
        this.batch = batch;
        this.status = status;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
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
