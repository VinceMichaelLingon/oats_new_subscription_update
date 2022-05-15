package com.example.oatsv5.Models;

import com.google.gson.annotations.SerializedName;

public class RegisterGuestResult {
    @SerializedName("msg")
    private String msg;

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }
}
