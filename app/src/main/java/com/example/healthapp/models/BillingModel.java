package com.example.healthapp.models;

public class BillingModel {
    private String userID;
    private String doctorName;
    private String amount;
    private String status;

    public BillingModel(String userID, String doctorName, String amount, String status) {
        this.userID = userID;
        this.doctorName = doctorName;
        this.amount = amount;
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
