package com.example.oatsv5.Models.Thesis;

public class ThesisAuthors {
    private String fname;
    private String lname;
    private String _id;

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String get_id() {
        return _id;
    }


    public ThesisAuthors(String fname, String lname, String _id) {
        this.fname = fname;
        this.lname = lname;
        this._id = _id;
    }
}
