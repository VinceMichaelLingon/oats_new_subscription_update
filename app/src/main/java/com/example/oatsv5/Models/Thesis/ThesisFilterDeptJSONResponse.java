package com.example.oatsv5.Models.Thesis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThesisFilterDeptJSONResponse {
    @SerializedName("theses")
    @Expose
    private ThesesResult[] thesesArray;

    public ThesesResult[] getThesesArray() {
        return thesesArray;
    }

    public ThesisFilterDeptJSONResponse(ThesesResult[] thesesArray) {
        this.thesesArray = thesesArray;
    }
}
