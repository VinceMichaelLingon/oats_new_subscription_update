package com.example.oatsv5.Models.Departments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepartmentJSONResponse {

    @SerializedName("department")
    private Departments[] departmentResult;

    public Departments[] getDepartmentResult() {
        return departmentResult;
    }

    public void setDepartmentResult(Departments[] departmentResult) {
        this.departmentResult = departmentResult;
    }
}
