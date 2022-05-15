package com.example.oatsv5.Models.Bookmarks;

import com.google.gson.annotations.SerializedName;

public class BookmarkedResult {
    private String user_id;
    private String _id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @SerializedName("thesis")
    private ThesisDetails thesisDetails;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


    public ThesisDetails getThesisDetails() {
        return thesisDetails;
    }

    public void setThesisDetails(ThesisDetails thesisDetails) {
        this.thesisDetails = thesisDetails;
    }

    public BookmarkedResult(String user_id, String _id, ThesisDetails thesisDetails) {
        this.user_id = user_id;
        this._id = _id;
        this.thesisDetails = thesisDetails;
    }
}
