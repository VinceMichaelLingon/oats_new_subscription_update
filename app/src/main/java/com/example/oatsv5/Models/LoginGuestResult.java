package com.example.oatsv5.Models;

import com.example.oatsv5.Models.Thesis.ThesesResult;
import com.google.gson.annotations.SerializedName;

public class LoginGuestResult {
    private String email;
    private String password;

    private String token;

    @SerializedName("guest")
    private GuestUser guestUser;

    @SerializedName("subType")
    private GuestSub guestSub;


    public GuestUser getGuestUser() {
        return guestUser;
    }

    public LoginGuestResult(GuestUser guestUser) {
        this.guestUser = guestUser;
    }

    public void setGuestUser(GuestUser guestUser) {
        this.guestUser = guestUser;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public GuestSub getGuestSub() {
        return guestSub;
    }

    public void setGuestSub(GuestSub guestSub) {
        this.guestSub = guestSub;
    }
}
