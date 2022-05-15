package com.example.oatsv5.Models.Courses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoursesJSONResponse {
    @SerializedName("course")
    @Expose
    private Courses[] courses;

    public Courses[] getCourses() {
        return courses;
    }

    public void setCourses(Courses[] courses) {
        this.courses = courses;
    }
}
