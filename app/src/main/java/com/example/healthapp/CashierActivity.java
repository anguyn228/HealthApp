package com.example.healthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.healthapp.adapters.BillingAdapter;
import com.example.healthapp.models.BillingModel;

import java.util.ArrayList;

public class CashierActivity extends AppCompatActivity {
    public static String USER_ID = "";
    private RecyclerView billingList;
    private BillingAdapter adapter;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier);

        USER_ID = getIntent().getStringExtra("userid");

        dataBaseHelper = new DataBaseHelper(this);

        billingList = findViewById(R.id.billingList);
        billingList.setLayoutManager(new LinearLayoutManager(this));

        retrieveBilling();
    }

    private void retrieveBilling(){
        ArrayList<BillingModel> list = dataBaseHelper.retrieveAllBillings();
        adapter = new BillingAdapter(this, list);
        billingList.setAdapter(adapter);
    }


}