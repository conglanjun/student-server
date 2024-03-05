package com.clj.student.service;

public enum UserStatus {
    NOTLOGIN("NOTLOGIN"),
    LOGIN("LOGIN"),
    SAVED("SAVED"),
    INCORRECTPASSWORD("INCORRECTPASSWORD");

    private String status;

    UserStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
