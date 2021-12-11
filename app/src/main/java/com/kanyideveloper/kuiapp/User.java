package com.kanyideveloper.kuiapp;

public class User {
    private String userId;
    private String userName;
    private String email;
    private String phoneNum;
    private String regNo;

    public User(){
    }

    public User(String userId, String userName, String email, String phoneNum, String regNo) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.regNo = regNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
}
