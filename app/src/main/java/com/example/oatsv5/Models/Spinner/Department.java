package com.example.oatsv5.Models.Spinner;

public class Department {
    public String id;
    public String deptName;

    public Department(String id, String deptName) {
        this.id = id;
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return deptName;
    }
}
