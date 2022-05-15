package com.example.oatsv5.Models;

import com.example.oatsv5.Models.Courses.Courses;
import com.example.oatsv5.Models.Courses.CoursesJSONResponse;
import com.example.oatsv5.Models.Departments.Departments;

public class GuestUser {
    private String _id;
    private String guest_fname;
    private String guest_lname;
    private String guest_contact;
    private String guest_profession;
    private String guest_company;
    private String guest_company_address;
    private String guest_mail;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getGuest_fname() {
        return guest_fname;
    }

    public void setGuest_fname(String guest_fname) {
        this.guest_fname = guest_fname;
    }

    public String getGuest_lname() {
        return guest_lname;
    }

    public void setGuest_lname(String guest_lname) {
        this.guest_lname = guest_lname;
    }

    public String getGuest_contact() {
        return guest_contact;
    }

    public void setGuest_contact(String guest_contact) {
        this.guest_contact = guest_contact;
    }

    public String getGuest_profession() {
        return guest_profession;
    }

    public void setGuest_profession(String guest_profession) {
        this.guest_profession = guest_profession;
    }

    public String getGuest_company() {
        return guest_company;
    }

    public void setGuest_company(String guest_company) {
        this.guest_company = guest_company;
    }

    public String getGuest_company_address() {
        return guest_company_address;
    }

    public void setGuest_company_address(String guest_company_address) {
        this.guest_company_address = guest_company_address;
    }

    public String getGuest_mail() {
        return guest_mail;
    }

    public void setGuest_mail(String guest_mail) {
        this.guest_mail = guest_mail;
    }

    public GuestUser(String _id, String guest_fname, String guest_lname, String guest_contact, String guest_profession, String guest_company, String guest_company_address, String guest_mail) {
        this._id = _id;
        this.guest_fname = guest_fname;
        this.guest_lname = guest_lname;
        this.guest_contact = guest_contact;
        this.guest_profession = guest_profession;
        this.guest_company = guest_company;
        this.guest_company_address = guest_company_address;
        this.guest_mail = guest_mail;
    }
}
