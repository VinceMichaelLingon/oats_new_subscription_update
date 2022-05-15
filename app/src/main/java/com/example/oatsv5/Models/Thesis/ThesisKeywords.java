package com.example.oatsv5.Models.Thesis;

public class ThesisKeywords {

    private String _id;
    private String keyword;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ThesisKeywords(String _id, String keyword) {
        this._id = _id;
        this.keyword = keyword;
    }
}
