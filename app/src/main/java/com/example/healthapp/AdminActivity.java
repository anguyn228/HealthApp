package com.example.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class AdminActivity extends AppCompatActivity {

    // references to buttons and editext
    Button btn_add, btn_viewAll, btn_update;
    EditText et_name, et_title, et_userId, et_password;
    ListView lv_dataList;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btn_update = findViewById(R.id.btn_update);
        btn_add = findViewById(R.id.btn_add);
        btn_viewAll = findViewById(R.id.btn_viewAll);
        et_name=findViewById(R.id.et_name);
        et_title = findViewById(R.id.et_title);
        et_userId = findViewById(R.id.et_userId);
        et_password = findViewById(R.id.et_password);
        lv_dataList = findViewById(R.id.lv_dataList);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(AdminActivity.this);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataModel dataModel;
                try {
                    dataModel = new DataModel(-1,et_name.getText().toString(), et_title.getText().toString(),
                            et_userId.getText().toString(),et_password.getText().toString());
                    Toast.makeText(AdminActivity.this, dataModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(AdminActivity.this, "Error creating user", Toast.LENGTH_SHORT).show();
                    dataModel = new DataModel(-1, "error", "","","");
                }



                boolean success= dataBaseHelper.addUser(dataModel);
                Toast.makeText(AdminActivity.this, "Success "+success, Toast.LENGTH_SHORT).show();
                ShowUsersOnListView(dataBaseHelper);
            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(AdminActivity.this);
                List<DataModel> everyone = dataBaseHelper.retrieveAllUsers();

                ShowUsersOnListView(dataBaseHelper);
                //Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,UpdateAdminActivity.class));
            }
        });

        lv_dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataModel clickedData = (DataModel) parent.getItemAtPosition(position);
                dataBaseHelper.deleteOne(clickedData);
                ShowUsersOnListView(dataBaseHelper);
                Toast.makeText(AdminActivity.this, "Deleted "+clickedData.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ShowUsersOnListView(DataBaseHelper dataBaseHelper2) {
        arrayAdapter = new ArrayAdapter<DataModel>(AdminActivity.this, android.R.layout.simple_list_item_1,dataBaseHelper2.retrieveAllUsers());
        lv_dataList.setAdapter(arrayAdapter);
    }
}