package com.example.healthapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthapp.models.BillingModel;
import com.example.healthapp.models.MessageModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import static com.example.healthapp.PatientActivity.USER_ID;

public class DoctorDetails extends AppCompatActivity {
    private String ID;
    private String Name;
    private Button bookingBtn;
    private Button onlineBtn;

    private DataBaseHelper dataBaseHelper;
    private TextView info;

    private ArrayList<DoctorModel> modelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        dataBaseHelper = new DataBaseHelper(this);

        if (getIntent() != null) {
            ID = getIntent().getStringExtra("userid");
            Name = getIntent().getStringExtra("name");
        }

        info = findViewById(R.id.info);
        bookingBtn = findViewById(R.id.bookingBtn);
        onlineBtn = findViewById(R.id.onlineBtn);

        bookingBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Appointment sent", Toast.LENGTH_SHORT).show();
            finish();
        });

        onlineBtn.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(DoctorDetails.this);
            AlertDialog dialog = builder.create();

            View view = LayoutInflater.from(DoctorDetails.this)
                    .inflate(R.layout.custom_input, null);
            TextInputEditText addressTF = view.findViewById(R.id.addressTF);
            Button searchBtn = view.findViewById(R.id.searchBtn);
            searchBtn.setOnClickListener(vw -> {
                String message = addressTF.getText().toString().trim();
                if (message.isEmpty()) {
                    Toast.makeText(this, "Message is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                // send message
                MessageModel messageModel = new MessageModel(USER_ID, ID, message);
                if (dataBaseHelper.addMessage(messageModel)) {
                    Toast.makeText(DoctorDetails.this, "Message was sent successfully", Toast.LENGTH_SHORT).show();
                }

                BillingModel billingModel = new BillingModel(USER_ID, ID, "200", "Unpaid");
                dataBaseHelper.addBilling(billingModel);
                dialog.dismiss();
            });

            dialog.setTitle("Enter Message");
            dialog.setView(view);
            dialog.show();
        });

        getDetails();

    }

    private void getDetails() {
        modelArrayList = dataBaseHelper.allDoctors();
        StringBuilder str = new StringBuilder();

        for (DoctorModel model : modelArrayList) {
            if (model.getUserID().equalsIgnoreCase(ID)) {
                str.append(" DOCTOR NAME : " + Name + "\n");
                str.append(" TITLE : " + model.getTitle() + "\n");
                str.append(" Office Information : " + model.getOfficeDetails() + "\n");
                str.append(" SHIFT : " + model.getShift() + "\n");
                str.append("\n");

                info.setText(str.toString());
            }
        }
    }
}