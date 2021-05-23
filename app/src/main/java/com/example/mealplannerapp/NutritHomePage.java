package com.example.mealplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NutritHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrit_home_page);
    }

    public void goToNutritProfileScreen(View view) {

        Intent intent = new Intent(this, NutritProfile.class);

        startActivity(intent);
    }

    public void goToPatientScreen(View view) {


        Intent intent = new Intent(this, NutritPatients.class);

        startActivity(intent);
    }
}