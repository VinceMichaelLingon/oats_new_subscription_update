package com.example.oatsv5.Models.Courses;

import com.example.oatsv5.Models.Departments.Departments;

public class Courses {
    private String _id;
    private String coursename;
    private String coursecode;

    private Departments departments;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode;
    }

    public Departments getDepartments() {
        return departments;
    }

    public void setDepartments(Departments departments) {
        this.departments = departments;
    }

    public Courses(String _id, String coursename, String coursecode, Departments departments) {
        this._id = _id;
        this.coursename = coursename;
        this.coursecode = coursecode;
        this.departments = departments;
    }
}
