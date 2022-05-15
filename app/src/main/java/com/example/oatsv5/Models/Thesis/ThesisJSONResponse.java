package com.example.oatsv5.Models.Thesis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThesisJSONResponse {

    @SerializedName("thesis")
    @Expose
    private ThesesResult[] thesesArray;


    public ThesesResult[] getThesesArray() {
        return thesesArray;
    }

    public void setThesesArray(ThesesResult[] thesesArray) {
        this.thesesArray = thesesArray;
    }





}
