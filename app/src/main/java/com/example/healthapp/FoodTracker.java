package com.example.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;

public class FoodTracker extends AppCompatActivity {
    private TextInputEditText vegeTF;
    private TextInputEditText fruitsTF;
    private TextInputEditText proteinsTF;
    private TextInputEditText caboTF;
    private Button calCaloriesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_tracker);

        vegeTF = findViewById(R.id.vegesTF);
        fruitsTF = findViewById(R.id.fruitsTF);
        proteinsTF = findViewById(R.id.proteinsTF);
        caboTF = findViewById(R.id.caboTF);
        calCaloriesBtn = findViewById(R.id.calCaloriesBtn);

        calCaloriesBtn.setOnClickListener(v -> {
            String veges = vegeTF.getText().toString().trim();
            String fruits = fruitsTF.getText().toString().trim();
            String proteins = proteinsTF.getText().toString().trim();
            String carbo = caboTF.getText().toString().trim();

            calculateCalories(veges, fruits, proteins, carbo);
        });
    }

    private void calculateCalories(String veges, String fruits, String proteins, String carbo) {
        Random random = new Random();
        int cal = random.nextInt(20);
        Toast.makeText(this, "Your today calories: " + cal, Toast.LENGTH_LONG).show();
        finish();
    }
}