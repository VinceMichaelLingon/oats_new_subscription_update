package com.example.oatsv5.Models.StudentAuth;

public class StudentSub {
    private String sub_type;
    private String status;

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StudentSub(String sub_type, String status) {
        this.sub_type = sub_type;
        this.status = status;
    }
}
