package com.example.mealplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);
    }

    public void goToProfileScreen(View view) {
        // Do something in response to button
        Intent intent = new Intent (this, UserProfile.class);
        startActivity(intent);
    }

    public void goToMealPlanScreen(View view) {
        // Do something in response to button
        Intent intent = new Intent (this, MealPlanPage.class);
        startActivity(intent);
    }

    public void goToShoppingScreen(View view) {
        // Do something in response to button
        Intent intent = new Intent (this, ShoppingList.class);
        startActivity(intent);
    }

    public void goToRecipeScreen(View view) {
        // Do something in response to button
        Intent intent = new Intent (this, UserProfile.class);
        startActivity(intent);
    }
}