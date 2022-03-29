package com.example.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DoctorActivity extends AppCompatActivity {
    DataBaseHelper databaseHelper;
    public static String ID = "", TITLE = "", NAME = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        ID = getIntent().getStringExtra("userid");
        TITLE = getIntent().getStringExtra("title");
        NAME = getIntent().getStringExtra("name");

        databaseHelper = new DataBaseHelper(this);
        TextView yourID = findViewById(R.id.txtID);
        Button viewINFO = findViewById(R.id.btnViewInfo);
        Button updateINFO = findViewById(R.id.btnUpdate);
        Button btnpCheck = findViewById(R.id.btnPCheck);
        TextView result = findViewById(R.id.txtResult);

        viewINFO.setOnClickListener(v -> {
            StringBuilder str = new StringBuilder();
            for (DoctorModel model : databaseHelper.allDoctors()) {
                if (model.getUserID().equalsIgnoreCase(ID)) {
                    str.append(" Name : " + NAME + "\n");
                    str.append(" TITLE : " + model.getTitle() + "\n");
                    str.append(" Office Information : " + model.getOfficeDetails() + "\n");
                    str.append(" SHIFT : " + model.getShift() + "\n");
                    str.append("\n");

                    result.setText(str.toString());
                }
            }


        });

        btnpCheck.setOnClickListener(v -> {
            startActivity(new Intent(DoctorActivity.this, Messages.class)
            .putExtra("userid", ID));
        });

        updateINFO.setOnClickListener(v -> startActivity(new Intent(DoctorActivity.this, UpdateDocActivity.class)
                .putExtra("uid", ID)
                .putExtra("title", TITLE)));
    }
}