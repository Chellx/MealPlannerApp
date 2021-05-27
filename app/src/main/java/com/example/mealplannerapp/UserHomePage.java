package com.example.mealplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class UserHomePage extends AppCompatActivity {
    String userEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);
        userEmail = getIntent().getExtras().getString("email");
    }

    public void goToProfileScreen(View view) {

        Bundle bundle = new Bundle();
        bundle.putString("email",userEmail);
        Intent intent = new Intent(this, UserProfile.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void goToMealPlanScreen(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("email",userEmail);
        // Do something in response to button
        Intent intent = new Intent (this, MealPlanPage.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void goToShoppingScreen(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("email",userEmail);
        // Do something in response to button
        Intent intent = new Intent (this, ShoppingHomePage.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void goToRecipeScreen(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("email",userEmail);
        // Do something in response to button
        Intent intent = new Intent (this, NutritRecipe.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void logOut(View view) {
        // Do something in response to button
        Intent intent = new Intent (this, LoginAndRegister.class);
        startActivity(intent);
    }


}