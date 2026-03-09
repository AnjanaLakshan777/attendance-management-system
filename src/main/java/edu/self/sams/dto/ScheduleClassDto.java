package edu.self.sams.dto;

public class ScheduleClassDto {
    private String classId;
    private String courseCode;
    private String subjectCode;
    private String userId;
    private String date;
    private String startTime;
    private String endTime;

    public ScheduleClassDto() {
    }

    public ScheduleClassDto(String classId, String courseCode, String subjectCode, String userId, String date, String startTime, String endTime) {
        this.classId = classId;
        this.courseCode = courseCode;
        this.subjectCode = subjectCode;
        this.userId = userId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
