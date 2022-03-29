package com.example.healthapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class PatientActivity extends AppCompatActivity {
    private Button foodTrackerBtn;
    private Button findDoctorBtn;
    private Button messagesBtn;
    private Button settingBtn;

    public static String USER_ID = "";
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        USER_ID = getIntent().getStringExtra("userid");
        dataBaseHelper = new DataBaseHelper(this);
        Toast.makeText(this, USER_ID + " >>> ", Toast.LENGTH_SHORT).show();
        foodTrackerBtn = findViewById(R.id.foodTrackerBtn);
        findDoctorBtn = findViewById(R.id.findDoctorBtn);
        messagesBtn = findViewById(R.id.messagesBtn);
        settingBtn = findViewById(R.id.settingBtn);

        foodTrackerBtn.setOnClickListener(v -> {
            startActivity(new Intent(PatientActivity.this, FoodTracker.class));
        });

        findDoctorBtn.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(PatientActivity.this);
            AlertDialog dialog = builder.create();

            View view = LayoutInflater.from(PatientActivity.this)
                    .inflate(R.layout.custom_input, null);
            TextInputEditText addressTF = view.findViewById(R.id.addressTF);
            Button searchBtn = view.findViewById(R.id.searchBtn);
            searchBtn.setOnClickListener(vw -> {
                String address = addressTF.getText().toString().trim();
                if (address.isEmpty()) {
                    Toast.makeText(this, "Address is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(new Intent(PatientActivity.this, FindDoctor.class)
                .putExtra("address", address));
                dialog.dismiss();
            });

            dialog.setTitle("Enter address");
            dialog.setView(view);
            dialog.show();

        });

        messagesBtn.setOnClickListener(v -> {
            startActivity(new Intent(PatientActivity.this, Messages.class)
            .putExtra("userid", USER_ID));
        });

        settingBtn.setOnClickListener(vv -> {
            startActivity(new Intent(PatientActivity.this, MyAccount.class)
            .putExtra("userid", USER_ID));
        });
    }
}