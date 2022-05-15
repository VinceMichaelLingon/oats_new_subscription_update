package com.example.oatsv5.Models.Spinner;

public class Course {
    public String id;
    public String courseName;

    public Course(String id, String courseName) {
        this.id = id;
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return courseName;
    }
}
