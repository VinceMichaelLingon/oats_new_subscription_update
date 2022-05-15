package com.example.oatsv5.Models.StudentAuth;

import com.google.gson.annotations.SerializedName;

public class StudentCourse {
    @SerializedName("courses")
    private String courseId;

    private String coursecode;

    private String coursename;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public StudentCourse(String courseId, String coursecode, String coursename) {
        this.courseId = courseId;
        this.coursecode = coursecode;
        this.coursename = coursename;
    }
}
