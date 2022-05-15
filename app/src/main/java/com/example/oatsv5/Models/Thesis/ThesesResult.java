package com.example.oatsv5.Models.Thesis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThesesResult  {



    private  String _id;
    private String title;


    private String token;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @SerializedName("publishedAt")
    private Integer published;


    @SerializedName("abstract")
    private String abstrct;


    private String status;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    //making separate class to call nested objects and arrays
    @SerializedName("department")
    private MoreDepartments moredepartments;

    public MoreDepartments getMoredepartments() {
        return moredepartments;
    }

    public void setMoredepartments(MoreDepartments moredepartments) {
        this.moredepartments = moredepartments;
    }

    @SerializedName("course")
    private MoreCourse moreCourse;

    public MoreCourse getMoreCourse() {
        return moreCourse;
    }

    public void setMoreCourse(MoreCourse moreCourse) {
        this.moreCourse = moreCourse;
    }

    @SerializedName("keywords")
    private ThesisKeywords[] thesisKeywords;

    public ThesisKeywords[] getThesisKeywords() {
        return thesisKeywords;
    }

    public void setThesisKeywords(ThesisKeywords[] thesisKeywords) {
        this.thesisKeywords = thesisKeywords;
    }

    @SerializedName("authors")
    private ThesisAuthors[] thesisAuthors;

    public ThesisAuthors[] getThesisAuthors() {
        return thesisAuthors;
    }

    public void setThesisAuthors(ThesisAuthors[] thesisAuthors) {
        this.thesisAuthors = thesisAuthors;
    }



    //Constructors

    public ThesesResult(String _id, String title, String token, Integer published, String abstrct, String upload, String status, MoreDepartments moredepartments, MoreCourse moreCourse, ThesisKeywords[] thesisKeywords, ThesisAuthors[] thesisAuthors, String base64) {
        this._id = _id;
        this.title = title;
        this.token = token;
        this.published = published;
        this.abstrct = abstrct;

        this.status = status;
        this.moredepartments = moredepartments;
        this.moreCourse = moreCourse;
        this.thesisKeywords = thesisKeywords;
        this.thesisAuthors = thesisAuthors;
        this.base64 = base64;
    }


    //    private Object department;

//    private Object course;

    //getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public String getAbstrct() {
        return abstrct;
    }

    public void setAbstrct(String abstrct) {
        this.abstrct = abstrct;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public Object getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(Object department) {
//        this.department = department;
//    }

//    public Object getCourse() {
//        return course;
//    }
//
//    public void setCourse(Object course) {
//        this.course = course;
//    }

    @SerializedName("upload")
    @Expose
    private String base64;

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
