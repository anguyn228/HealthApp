package com.example.healthapp;

public class DoctorModel {
    private int id;
    private String userID;
    private String name;
    private String title;
    private String officeDetails;
    private String shift;

    //constructors


    public DoctorModel(String userID, String name, String title, String officeDetails, String shift) {
        this.name = name;
        this.title = title;
        this.officeDetails = officeDetails;
        this.shift = shift;
        this.userID = userID;
    }



    public DoctorModel(String userID, String title, String officeDetails, String shift) {
        this.userID = userID;
        this.title = title;
        this.officeDetails = officeDetails;
        this.shift = shift;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    // toString method
    @Override
    public String toString() {
        return  "  ID: " + id +
                "  Title: '" + title + '\'' +
                "  Office Details: '" + officeDetails + '\'' +
                "  Shift:'" + shift + '\'';
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {

        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOfficeDetails() {
        return officeDetails;
    }

    public void setOfficeDetails(String officeDetails) {
        this.officeDetails = officeDetails;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }


}
