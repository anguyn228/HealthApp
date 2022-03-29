package com.example.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private TextInputEditText emailTF;
    private TextInputEditText passwordTF;
    private Button signInBtn;
    private Button signUpBtn;

    private ProgressDialog dialog;
    private DataBaseHelper dataBaseHelper;
    private ArrayList<DataModel> users;

    private String USERID = "", TITLE = "", NAME = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dialog = new ProgressDialog(this);
        dataBaseHelper = new DataBaseHelper(this);

        users = dataBaseHelper.retrieveAllUsers();

        emailTF = findViewById(R.id.emailTF);
        passwordTF = findViewById(R.id.passwordTF);
        signInBtn = findViewById(R.id.signInBtn);
        signUpBtn = findViewById(R.id.signUpBtn);

        signInBtn.setOnClickListener(v -> {
            // get user login credentials
            String username = emailTF.getText().toString().trim();
            String password = passwordTF.getText().toString().trim();

            // validate credentials
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Username and password required", Toast.LENGTH_SHORT).show();
                return;
            }
            if (username.equalsIgnoreCase("admin") && password.equals("admin")) {
                // default user
                startActivity(new Intent(Login.this, AdminActivity.class));
                finish();
            } else {
                signIn(username, password);
            }
        });

        signUpBtn.setOnClickListener(v -> {
            startActivity(new Intent(Login.this, Register.class));
        });
    }

    private void signIn(String username, String password) {
        dialog.setMessage("Please wait...");
        dialog.show();
        boolean status = false;
        // find if this user exists
        for (DataModel d : users) {
            if (d.getUserId().equalsIgnoreCase(username) && d.getPassword().equals(password)) {
                // users exists - get the role
                String role = d.getTitle();
                USERID = d.getUserId();
                TITLE = d.getTitle();
                NAME = d.getName();
                signInByRole(role);
                status = true;
                dialog.dismiss();
                break;
            }
        }

        if (!status) {
            Toast.makeText(this, "Login failed. Incorrect username or password", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }

    }

    private void signInByRole(String role) {
        switch (role.toLowerCase()) {
            case "admin":
                startActivity(new Intent(Login.this, AdminActivity.class)
                        .putExtra("userid", USERID));
                finish();
                break;
            case "doctor":
                startActivity(new Intent(Login.this, DoctorActivity.class)
                        .putExtra("userid", USERID)
                        .putExtra("title", TITLE)
                        .putExtra("name", NAME));
                finish();
                break;
            case "patient":
                startActivity(new Intent(Login.this, PatientActivity.class)
                        .putExtra("userid", USERID));
                finish();
                break;
            case "cashier":
                startActivity(new Intent(Login.this, CashierActivity.class)
                        .putExtra("userid", USERID));
                finish();
                break;
            default:
                break;
        }
    }
}