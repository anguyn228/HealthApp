package com.example.healthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.healthapp.adapters.DoctosListAdapter;

import java.util.ArrayList;

public class FindDoctor extends AppCompatActivity {
    private RecyclerView doctorsList;
    private DataBaseHelper dataBaseHelper;
    private DoctosListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        dataBaseHelper = new DataBaseHelper(this);

        doctorsList = findViewById(R.id.doctorsList);
        doctorsList.setLayoutManager(new LinearLayoutManager(this));

        retrieveAllDoctors();
    }

    private void retrieveAllDoctors() {
        ArrayList<DataModel> list = dataBaseHelper.retrieveAllUsers();
        ArrayList<DataModel> doctors = new ArrayList<>();

        for(DataModel m : list){
            if(m.getTitle().equalsIgnoreCase("doctor")){
                doctors.add(m);
            }
        }
        adapter = new DoctosListAdapter(this, doctors);
        doctorsList.setAdapter(adapter);
    }
}