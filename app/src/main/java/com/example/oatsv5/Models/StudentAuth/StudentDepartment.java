package com.example.oatsv5.Models.StudentAuth;

import com.google.gson.annotations.SerializedName;

public class StudentDepartment {

    @SerializedName("departments")
    private String deptId;

    private String deptname;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public StudentDepartment(String deptId, String deptname) {
        this.deptId = deptId;
        this.deptname = deptname;
    }
}
