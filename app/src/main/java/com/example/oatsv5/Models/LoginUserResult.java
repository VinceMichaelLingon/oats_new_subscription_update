package com.example.oatsv5.Models;

import com.example.oatsv5.Models.Departments.Departments;
import com.google.gson.annotations.SerializedName;

public class LoginUserResult {

    private String email;


    private String password;

    @SerializedName("department")
    private Departments departments;

    public Departments getDepartments() {
        return departments;
    }

    public void setDepartments(Departments departments) {
        this.departments = departments;
    }

    public LoginUserResult(Departments departments) {
        this.departments = departments;
    }

    private String courses;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }



    public String getCourses() {
        return courses;
    }



    public void setCourses(String courses) {
        this.courses = courses;
    }
}
