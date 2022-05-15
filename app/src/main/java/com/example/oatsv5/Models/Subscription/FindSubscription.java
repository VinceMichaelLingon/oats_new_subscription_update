package com.example.oatsv5.Models.Subscription;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FindSubscription {
    @SerializedName("subscription")
    @Expose
    private Subscription subscription;

    @SerializedName("success")
    private Boolean success;

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
