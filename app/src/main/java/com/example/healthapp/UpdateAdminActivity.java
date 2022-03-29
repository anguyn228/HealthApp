package com.example.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateAdminActivity extends AppCompatActivity {
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_admin);
        dataBaseHelper = new DataBaseHelper(this);

        EditText userID = findViewById(R.id.et_userIdUpdate);
        EditText password = findViewById(R.id.et_updatePassword);
        Button btnApply = findViewById(R.id.btn_apply);
        Button btnHome = findViewById(R.id.btn_home);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateAdminActivity.this, Login.class));
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName, newPassword;
                userName  = userID.getText().toString();
                newPassword = password.getText().toString();
                boolean updated = dataBaseHelper.updateUser(userName,newPassword);
                if(updated){
                    Toast.makeText(UpdateAdminActivity.this,"Record updated",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(UpdateAdminActivity.this,"Record not updated",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}