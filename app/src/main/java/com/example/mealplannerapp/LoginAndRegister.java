package com.example.mealplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginAndRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_register);
    }

    /** Called when the user taps the Send button */
    public void goToRegisterScreen(View view) {
        // Do something in response to button


        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }

    //go to login screen
    public void goToLoginScreen(View view){
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
    }

}