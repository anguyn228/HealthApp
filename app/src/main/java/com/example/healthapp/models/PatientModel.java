package com.example.healthapp.models;

public class PatientModel {
    private String userID;
    private String diseases;
    private String allergies;
    private String medication;

    public PatientModel(String userID, String diseases, String allergies, String medication) {
        this.userID = userID;
        this.diseases = diseases;
        this.allergies = allergies;
        this.medication = medication;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDiseases() {
        return diseases;
    }

    public void setDiseases(String diseases) {
        this.diseases = diseases;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }
}
