package edu.self.sams.dto;

public class LecturerDto {
    private String userId;
    private String name;
    private String email;
    private String teleNo;
    private String password;

    public LecturerDto() {
    }

    public LecturerDto(String userId, String name, String email, String teleNo, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.teleNo = teleNo;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
