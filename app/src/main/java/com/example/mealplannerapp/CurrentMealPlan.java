package com.example.mealplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CurrentMealPlan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_meal_plan);
    }

    public void gobackHome (View view){
        // Do something in response to button
        Intent intent = new Intent (this, UserHomePage.class);
        startActivity(intent);
    }
}