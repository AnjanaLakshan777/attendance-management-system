package edu.self.sams.dto;

public class SubjectLecturerDto {
    private String subjectCode;
    private String subjectName;
    private String userId;
    private String lecturerName;

    public SubjectLecturerDto() {
    }

    public SubjectLecturerDto(String subjectCode, String subjectName, String userId, String lecturerName) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.userId = userId;
        this.lecturerName = lecturerName;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }
}
