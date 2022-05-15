package com.example.oatsv5.Models.StudentAuth;

import com.google.gson.annotations.SerializedName;

public class StudentUser {
    private String user_tupid;
    private String user_fname;
    private String user_lname;
    private String user_contact;
    private String user_tupmail;
    private String user_status;

    @SerializedName("user_department")
    private StudentDepartment studentDepartment;

    @SerializedName("user_course")
    private  StudentCourse studentCourse;


    public String getUser_tupid() {
        return user_tupid;
    }

    public void setUser_tupid(String user_tupid) {
        this.user_tupid = user_tupid;
    }

    public String getUser_fname() {
        return user_fname;
    }

    public void setUser_fname(String user_fname) {
        this.user_fname = user_fname;
    }

    public String getUser_lname() {
        return user_lname;
    }

    public void setUser_lname(String user_lname) {
        this.user_lname = user_lname;
    }

    public String getUser_tupmail() {
        return user_tupmail;
    }

    public void setUser_tupmail(String user_tupmail) {
        this.user_tupmail = user_tupmail;
    }

    public String getUser_contact() {
        return user_contact;
    }

    public void setUser_contact(String user_contact) {
        this.user_contact = user_contact;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public StudentDepartment getStudentDepartment() {
        return studentDepartment;
    }

    public void setStudentDepartment(StudentDepartment studentDepartment) {
        this.studentDepartment = studentDepartment;
    }

    public StudentCourse getStudentCourse() {
        return studentCourse;
    }

    public void setStudentCourse(StudentCourse studentCourse) {
        this.studentCourse = studentCourse;
    }

    public StudentUser(String user_tupid, String user_fname, String user_lname, String user_contact, String user_tupmail, String user_status, StudentDepartment studentDepartment, StudentCourse studentCourse) {
        this.user_tupid = user_tupid;
        this.user_fname = user_fname;
        this.user_lname = user_lname;
        this.user_contact = user_contact;
        this.user_tupmail = user_tupmail;
        this.user_status = user_status;
        this.studentDepartment = studentDepartment;
        this.studentCourse = studentCourse;
    }
}
