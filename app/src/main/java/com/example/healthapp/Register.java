package com.example.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.healthapp.models.PatientModel;
import com.google.android.material.textfield.TextInputEditText;

public class Register extends AppCompatActivity {
    private TextInputEditText emailTF;
    private TextInputEditText firstNameTF;
    private TextInputEditText lastNameTF;
    private TextInputEditText passwordTF;
    private TextInputEditText diseaseTF;
    private TextInputEditText allergiesTF;
    private TextInputEditText medicationTF;
    private Button signUpBtn;

    private ProgressDialog dialog;
    private DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dialog = new ProgressDialog(this);
        dataBaseHelper = new DataBaseHelper(this);

        emailTF = findViewById(R.id.emailTF);
        firstNameTF = findViewById(R.id.firstNameTF);
        lastNameTF = findViewById(R.id.lastNameTF);
        passwordTF = findViewById(R.id.passwordTF);
        signUpBtn = findViewById(R.id.signUpBtn);
        diseaseTF = findViewById(R.id.diseaseTF);
        allergiesTF = findViewById(R.id.allergiesTF);
        medicationTF = findViewById(R.id.medicationTF);

        signUpBtn.setOnClickListener(v -> {
            // get user inputs
            String userId = emailTF.getText().toString().trim();
            String fName = firstNameTF.getText().toString().trim();
            String lName = lastNameTF.getText().toString().trim();
            String password = passwordTF.getText().toString().trim();
            String diseases = diseaseTF.getText().toString().trim();
            String allergies = allergiesTF.getText().toString().trim();
            String medication = medicationTF.getText().toString().trim();

            // validate input
            if(userId.isEmpty() || fName.isEmpty() || lName.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Username, fistname, lastname and password are required", Toast.LENGTH_SHORT).show();
                return;
            }

            registerPatient(userId, fName, lName, password, diseases, allergies, medication);

        });
    }

    private void registerPatient(String userId, String fName, String lName, String password, String diseases, String allergies, String medication) {
        dialog.setMessage("Registering...");
        dialog.show();

        PatientModel patient = new PatientModel(userId, diseases, allergies, medication);
        String name = fName + " " + lName;
        String role = "Patient";
        DataModel data = new DataModel(name, role, userId, password);

        // save user to db
        if(dataBaseHelper.addUser(data)){ // user was added successfully
            // add patient's data
            if(dataBaseHelper.addPatientDetails(patient)){
                Toast.makeText(this, "Registration was successful", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                finish();
            }
        }else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
    }
}