package com.example.mealplannerapp;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME = 3000; // time for splash on screen is 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler ().postDelayed(new Runnable(){
            @Override
            public void run (){
                Intent homeScreenIntent = new Intent(SplashScreen.this,LoginAndRegister.class);
                startActivity(homeScreenIntent);
                finish();
            }
        },SPLASH_TIME);

    }
}