package com.example.mealplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NutritHomePage extends AppCompatActivity {
    String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrit_home_page);
        mail = getIntent().getExtras().getString("email");
    }

    public void goToNutritProfileScreen(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("email",mail);
        Intent intent = new Intent(this, NutritProfile.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void goToPatientScreen(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("email",mail);
        Intent intent = new Intent(this, NutritPatients.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void goToRecipeScreen(View view) {
        Intent intent = new Intent(this, NutritRecipe.class);
        startActivity(intent);
    }

    public void logOut(View view) {
        // Do something in response to button
        Intent intent = new Intent (this, LoginAndRegister.class);
        startActivity(intent);
    }
}