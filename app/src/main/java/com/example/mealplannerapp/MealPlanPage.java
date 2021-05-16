package com.example.mealplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MealPlanPage extends AppCompatActivity {
    String userEmail = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan_page);
        userEmail = getIntent().getExtras().getString("email");
    }


    public void gobackHome (View view){
        Bundle bundle = new Bundle();
        bundle.putString("email",userEmail);
        // Do something in response to button
        Intent intent = new Intent (this, UserHomePage.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void generateMealPlan (View view){
        Bundle bundle = new Bundle();
        bundle.putString("email",userEmail);
        bundle.putString("bool","0");
        // Do something in response to button
        Intent intent = new Intent (this, CurrentMealPlan.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void goToCurrentMealPlan (View view){
        Bundle bundle = new Bundle();
        bundle.putString("email",userEmail);
        bundle.putString("bool","1");
        // Do something in response to button
        Intent intent = new Intent (this, CurrentMealPlan.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}