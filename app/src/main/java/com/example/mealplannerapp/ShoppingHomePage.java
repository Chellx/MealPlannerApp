package com.example.mealplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ShoppingHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_home_page);
    }

    public void gobackHome (View view){
        // Do something in response to button
        Intent intent = new Intent (this, UserHomePage.class);
        startActivity(intent);
    }

    public void goToCreateNewShop (View view){
        // Do something in response to button
        Intent intent = new Intent (this, ShoppingList.class);
        startActivity(intent);
    }

    public void goToCurrentShopList (View view){
        // Do something in response to button
        Intent intent = new Intent (this, ViewCurrentShopList.class);
        startActivity(intent);
    }
}