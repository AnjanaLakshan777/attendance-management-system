package edu.self.sams.dto;

public class StudentDto {
    private String regNo;
    private String name;
    private String email;
    private String teleNo;

    public StudentDto() {
    }

    public StudentDto(String regNo, String name, String email, String teleNo) {
        this.regNo = regNo;
        this.name = name;
        this.email = email;
        this.teleNo = teleNo;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTeleNo() {
        return teleNo;
    }

    public void setTeleNo(String teleNo) {
        this.teleNo = teleNo;
    }
}
