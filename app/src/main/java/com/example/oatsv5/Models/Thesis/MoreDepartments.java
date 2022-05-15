package com.example.oatsv5.Models.Thesis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoreDepartments {


    private String _id;
    private String departments;
    private String deptname;



    public MoreDepartments(String _id, String deptname) {

    }

    public MoreDepartments() {

    }


    public String getDepartments() {
        return departments;
    }

    public String getDeptname() {
        return deptname;
    }



    public void setDepartments(String departments) {
        this.departments = departments;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public MoreDepartments(String _id, String departments, String deptname) {
        this._id = _id;
        this.departments = departments;
        this.deptname = deptname;
    }
}
