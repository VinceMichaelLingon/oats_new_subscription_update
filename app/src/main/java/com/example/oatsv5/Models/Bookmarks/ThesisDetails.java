package com.example.oatsv5.Models.Bookmarks;

import com.google.gson.annotations.SerializedName;

public class ThesisDetails {
    private String id;

    private String title;

    @SerializedName("abstract")
    private String thesisAbstract;

    private Number publishedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThesisAbstract() {
        return thesisAbstract;
    }

    public void setThesisAbstract(String thesisAbstract) {
        this.thesisAbstract = thesisAbstract;
    }

    public Number getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(int publishedAt) {
        this.publishedAt = publishedAt;
    }

    public ThesisDetails(String id, String title, String thesisAbstract, Number publishedAt) {
        this.id = id;
        this.title = title;
        this.thesisAbstract = thesisAbstract;
        this.publishedAt = publishedAt;
    }
}
