package com.example.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class MyAccount extends AppCompatActivity {
    private DataBaseHelper dataBaseHelper;
    private TextInputEditText emailTF;
    private TextInputEditText firstNameTF;
    private TextInputEditText lastNameTF;
    private TextInputEditText passwordTF;
    private Button updateBtn;
    private String User_Id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        dataBaseHelper = new DataBaseHelper(this);
        User_Id = getIntent().getStringExtra("userid");

        emailTF = findViewById(R.id.emailTF);
        firstNameTF = findViewById(R.id.firstNameTF);
        lastNameTF = findViewById(R.id.lastNameTF);
        passwordTF = findViewById(R.id.passwordTF);
        updateBtn = findViewById(R.id.updateBtn);

        updateBtn.setOnClickListener(v -> {
            String userId = emailTF.getText().toString().trim();
            String fName = firstNameTF.getText().toString().trim();
            String lName = lastNameTF.getText().toString().trim();
            String password = passwordTF.getText().toString().trim();

            // validate input
            if(userId.isEmpty() || fName.isEmpty() || lName.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Username, fistname, lastname and password are required", Toast.LENGTH_SHORT).show();
                return;
            }
            if(dataBaseHelper.updateUser(userId, password)){
                Toast.makeText(this, "User password updated!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        retrieveRecords();
    }

    private void retrieveRecords(){
        ArrayList<DataModel> list = dataBaseHelper.retrieveAllUsers();
        for(DataModel d : list){
            if(d.getUserId().equalsIgnoreCase(User_Id)){
                String[] name = d.getName().split(" ");
                emailTF.setText(d.getUserId());
                firstNameTF.setText(name[0]);
                lastNameTF.setText(name[1]);
                passwordTF.setText(d.getPassword());
                break;
            }
        }

    }
}