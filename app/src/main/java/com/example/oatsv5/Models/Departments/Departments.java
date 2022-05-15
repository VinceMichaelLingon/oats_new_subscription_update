package com.example.oatsv5.Models.Departments;

public class Departments {


    private String _id;
    private String departments;
    private String deptname;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public Departments(String _id, String departments, String deptname) {
        this._id = _id;
        this.departments = departments;
        this.deptname = deptname;
    }
}
