package com.example.healthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.healthapp.adapters.MessageListAdapter;
import com.example.healthapp.models.MessageModel;

import java.util.ArrayList;

import static com.example.healthapp.PatientActivity.USER_ID;

public class Messages extends AppCompatActivity {
    private RecyclerView messageList;
    private DataBaseHelper dataBaseHelper;
    private MessageListAdapter adapter;
    private String userID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        userID = getIntent().getStringExtra("userid");

        dataBaseHelper = new DataBaseHelper(this);
        messageList = findViewById(R.id.messageList);
        messageList.setLayoutManager(new LinearLayoutManager(this));

        getMyMessages();

    }

    private void getMyMessages(){
        ArrayList<MessageModel> myMsg = new ArrayList<>();
        ArrayList<MessageModel> allMsg = dataBaseHelper.retrieveAllMessages();

        for(MessageModel model : allMsg){
            if(model.getUserID().equalsIgnoreCase(userID) || model.getSender().equalsIgnoreCase(userID)){
                myMsg.add(model);
            }
        }

        adapter = new MessageListAdapter(this, myMsg);
        messageList.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}