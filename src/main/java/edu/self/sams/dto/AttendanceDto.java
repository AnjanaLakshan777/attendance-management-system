package edu.self.sams.dto;

public class AttendanceDto {
    private String classId;
    private String regNo;
    private String status;
    private String remark;

    public AttendanceDto() {
    }

    public AttendanceDto(String classId, String regNo, String status, String remark) {
        this.classId = classId;
        this.regNo = regNo;
        this.status = status;
        this.remark = remark;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
