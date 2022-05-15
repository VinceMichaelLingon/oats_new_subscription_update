package com.example.oatsv5.Models.Thesis;

import com.google.gson.annotations.SerializedName;

public class MoreCourse {
    @SerializedName("courses")
    private String courseId;
    private String coursecode;
    private String coursename;

    public String getCourseId() {
        return courseId;
    }

    public String getCoursecode() {
        return coursecode;
    }

    public String getCoursename() {
        return coursename;
    }


    public MoreCourse(String courseId, String coursecode, String coursename) {
        this.courseId = courseId;
        this.coursecode = coursecode;
        this.coursename = coursename;
    }
}
