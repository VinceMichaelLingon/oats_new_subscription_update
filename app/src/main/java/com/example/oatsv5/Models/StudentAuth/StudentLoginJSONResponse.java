package com.example.oatsv5.Models.StudentAuth;

import com.google.gson.annotations.SerializedName;

public class StudentLoginJSONResponse {
    private String email;
    private String password;

    private String token;

    @SerializedName("user")
    private StudentUser studentUser;

    @SerializedName("subType")
    private StudentSub studentSub;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public StudentUser getStudentUser() {
        return studentUser;
    }

    public void setStudentUser(StudentUser studentUser) {
        this.studentUser = studentUser;
    }

    public StudentSub getStudentSub() {
        return studentSub;
    }

    public void setStudentSub(StudentSub studentSub) {
        this.studentSub = studentSub;
    }
}
