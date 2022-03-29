package com.example.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class UpdateDocActivity extends AppCompatActivity {

    DataBaseHelper databaseHelper;
    private TextInputEditText uidTF;
    private TextInputEditText titleTF;
    private TextInputEditText officeTF;
    private TextInputEditText shiftTF;
    private Button btnUpdate;

    private String uid, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_doc);

        databaseHelper = new DataBaseHelper(this);

        Intent i = getIntent();
        if (i != null) {
            uid = i.getStringExtra("uid");
            title = i.getStringExtra("title");
        }


        btnUpdate = findViewById(R.id.btnUpdateNew);
        uidTF = findViewById(R.id.uIDTF);
        titleTF = findViewById(R.id.titleTF);
        officeTF = findViewById(R.id.officeTF);
        shiftTF = findViewById(R.id.shiftTF);


        btnUpdate.setOnClickListener(v -> {
            String office = officeTF.getText().toString().trim();
            String shift = shiftTF.getText().toString().trim();

            if (office.isEmpty() || shift.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            DoctorModel model = new DoctorModel(uid, title, office, shift);

            // check if this doctor exists or not
            if (!isDoctorExists(uid)) {
                if (databaseHelper.addDoctor(model)) {
                    Toast.makeText(this, "Doctor info captured successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Failed to add doctor info", Toast.LENGTH_SHORT).show();
                }
            } else {
                // update details
                if (databaseHelper.updateDoc(model)) {
                    Toast.makeText(this, "Doctor details updated successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateFields();

    }

    private void updateFields() {
        uidTF.setText(uid);
        titleTF.setText(title);
    }

    private boolean isDoctorExists(String id) {
        boolean status = false;
        for (DoctorModel d : databaseHelper.retrieveAllDoctors()) {
            if (d.getUserID().equalsIgnoreCase(id)) {
                status = true;
                break;
            }
        }
        return status;
    }
}