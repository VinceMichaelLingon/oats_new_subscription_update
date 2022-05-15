package com.example.oatsv5.Models.Bookmarks;

import com.google.gson.annotations.SerializedName;

public class BookmarkedJSONResponse {
    @SerializedName("bookmarks")
    private BookmarkedResult[] bookmarkedArray;

    public BookmarkedResult[] bookmarkedArray() {
        return bookmarkedArray;
    }

    public void setBookmarkedArray(BookmarkedResult[] bookmarkedArray) {
        this.bookmarkedArray = bookmarkedArray;
    }
}
