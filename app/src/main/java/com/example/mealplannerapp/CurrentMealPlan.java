package com.example.mealplannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CurrentMealPlan extends AppCompatActivity {
    private DatabaseReference ref;
    private String email = "";
    private String mealPlanMade = "";
    private Button mondayB,mondayL,mondayD,tuesdayB,tuesdayL,tuesdayD,wednesdayB,wednesdayL,wednesdayD,thursdayB,thursdayL,thursdayD,fridayB,fridayL,fridayD,saturdayB,saturdayL,saturdayD,sundayB,sundayL,sundayD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_meal_plan);
        email = getIntent().getExtras().getString("email");
        email=email.replace(".","");
        mondayB = findViewById(R.id.monBreak);
        mondayL = findViewById(R.id.monLunch);
        mondayD = findViewById(R.id.monDinner);
        tuesdayB = findViewById(R.id.tueBreak);
        tuesdayL = findViewById(R.id.tueLunch);
        tuesdayD = findViewById(R.id.tueDinner);
        wednesdayB = findViewById(R.id.wedBreak);
        wednesdayL = findViewById(R.id.wedLunch);
        wednesdayD = findViewById(R.id.wedDinner);
        thursdayB = findViewById(R.id.thursBreak);
        thursdayL = findViewById(R.id.thursLunch);
        thursdayD = findViewById(R.id.thursDinner);
        fridayB = findViewById(R.id.friBreak);
        fridayL = findViewById(R.id.friLunch);
        fridayD = findViewById(R.id.friDinner);
        saturdayB = findViewById(R.id.satBreak);
        saturdayL = findViewById(R.id.satLunch);
        saturdayD = findViewById(R.id.satDinner);
        sundayB = findViewById(R.id.sunBreak);
        sundayL = findViewById(R.id.sunLunch);
        sundayD = findViewById(R.id.sunDinner);
        mealPlanMade = getIntent().getExtras().getString("bool");
        if(mealPlanMade.equals("1")){
            addToButtonsFromDatabase();
        }
        else{
            getRecipesFromDatabase();
            addToButtonsFromDatabase();
        }
    }

    public void gobackHome (View view){
        // Do something in response to button
        Intent intent = new Intent (this, UserHomePage.class);
        startActivity(intent);
    }
    public void getRecipesFromDatabase(){
        ref = FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Recipes");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String ingredients = "";
                String keyName = "";
                String RecipeName = "";
                String method = "";
                ArrayList<String> breakfastRecipes = new ArrayList<String>();
                ArrayList<String> lunchRecipes = new ArrayList<String>();
                ArrayList<String> dinnerRecipes = new ArrayList<String>();
                for(DataSnapshot itemList: snapshot.getChildren()){
                    keyName = itemList.getKey();
                    for(DataSnapshot item: itemList.getChildren()){
                        RecipeName = item.getKey();
                        if(keyName.equals("Breakfast")){
                            breakfastRecipes.add(item.getKey());
                        }
                        if(keyName.equals("Dinner")){
                            dinnerRecipes.add(item.getKey());
                        }
                        if(keyName.equals("Lunch")){
                            lunchRecipes.add(item.getKey());
                        }
                    }
                }
                addRecipesToDatabase(breakfastRecipes,lunchRecipes,dinnerRecipes);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void addRecipesToDatabase( ArrayList<String> breakfastRecipes,ArrayList<String> lunchRecipes,ArrayList<String> dinnerRecipes){
        String recipes = "";
        HashMap<String,Object> userShopListMap = new HashMap<>();

        Collections.shuffle(breakfastRecipes);
        recipes = breakfastRecipes.get(0);
        userShopListMap.put("Monday BreakFast",recipes);
        Collections.shuffle(breakfastRecipes);
        recipes = breakfastRecipes.get(0);
        userShopListMap.put("Tuesday BreakFast",recipes);
        Collections.shuffle(breakfastRecipes);
        recipes =  breakfastRecipes.get(0);
        userShopListMap.put("Wednesday BreakFast",recipes);
        Collections.shuffle(breakfastRecipes);
        recipes =  breakfastRecipes.get(0);
        userShopListMap.put("Thursday BreakFast",recipes);
        Collections.shuffle(breakfastRecipes);
        recipes =  breakfastRecipes.get(0);
        userShopListMap.put("Friday BreakFast",recipes);
        Collections.shuffle(breakfastRecipes);
        recipes =  breakfastRecipes.get(0);
        userShopListMap.put("Saturday BreakFast",recipes);
        Collections.shuffle(breakfastRecipes);
        recipes =  breakfastRecipes.get(0);
        userShopListMap.put("Sunday BreakFast",recipes);

        Collections.shuffle(lunchRecipes);
        recipes = lunchRecipes.get(0);
        userShopListMap.put("Monday Lunch",recipes);
        Collections.shuffle(lunchRecipes);
        recipes = lunchRecipes.get(0);
        userShopListMap.put("Tuesday Lunch",recipes);
        Collections.shuffle(lunchRecipes);
        recipes = lunchRecipes.get(0);
        userShopListMap.put("Wednesday Lunch",recipes);
        Collections.shuffle(lunchRecipes);
        recipes = lunchRecipes.get(0);
        userShopListMap.put("Thursday Lunch",recipes);
        Collections.shuffle(lunchRecipes);
        recipes = lunchRecipes.get(0);
        userShopListMap.put("Friday Lunch",recipes);
        Collections.shuffle(lunchRecipes);
        recipes = lunchRecipes.get(0);
        userShopListMap.put("Saturday Lunch",recipes);
        Collections.shuffle(lunchRecipes);
        recipes = lunchRecipes.get(0);
        userShopListMap.put("Sunday Lunch",recipes);

        Collections.shuffle(dinnerRecipes);
        recipes = dinnerRecipes.get(0);
        userShopListMap.put("Monday Dinner",recipes);
        Collections.shuffle(dinnerRecipes);
        recipes = dinnerRecipes.get(0);
        userShopListMap.put("Tuesday Dinner",recipes);
        Collections.shuffle(dinnerRecipes);
        recipes = dinnerRecipes.get(0);
        userShopListMap.put("Wednesday Dinner",recipes);
        Collections.shuffle(dinnerRecipes);
        recipes = dinnerRecipes.get(0);
        userShopListMap.put("Thursday Dinner",recipes);
        Collections.shuffle(dinnerRecipes);
        recipes = dinnerRecipes.get(0);
        userShopListMap.put("Friday Dinner",recipes);
        Collections.shuffle(dinnerRecipes);
        recipes = dinnerRecipes.get(0);
        userShopListMap.put("Saturday Dinner",recipes);
        Collections.shuffle(dinnerRecipes);
        recipes = dinnerRecipes.get(0);
        userShopListMap.put("Sunday Dinner",recipes);
        FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Meal Plans").child(email).setValue(userShopListMap);
    }
    public void addToButtonsFromDatabase(){
        ref = FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Meal Plans").child(email);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,String> userShopListMap = new HashMap<>();
                for(DataSnapshot itemList: snapshot.getChildren()){
                    String key = itemList.getKey();
                    switch(key) {
                        case "Monday BreakFast":
                            userShopListMap.put("Monday BreakFast",itemList.getValue().toString());
                            break;
                        case "Tuesday BreakFast":
                            userShopListMap.put("Tuesday BreakFast",itemList.getValue().toString());
                            break;
                        case "Wednesday BreakFast":
                            userShopListMap.put("Wednesday BreakFast",itemList.getValue().toString());
                            break;
                        case "Thursday BreakFast":
                            userShopListMap.put("Thursday BreakFast",itemList.getValue().toString());
                            break;
                        case "Friday BreakFast":
                            userShopListMap.put("Friday BreakFast",itemList.getValue().toString());
                            break;
                        case "Saturday BreakFast":
                            userShopListMap.put("Saturday BreakFast",itemList.getValue().toString());
                            break;
                        case "Sunday BreakFast":
                            userShopListMap.put("Sunday BreakFast",itemList.getValue().toString());
                            break;
                        case "Monday Lunch":
                            userShopListMap.put("Monday Lunch",itemList.getValue().toString());
                            break;
                        case "Tuesday Lunch":
                            userShopListMap.put("Tuesday Lunch",itemList.getValue().toString());
                            break;
                        case "Wednesday Lunch":
                            userShopListMap.put("Wednesday Lunch",itemList.getValue().toString());
                            break;
                        case "Thursday Lunch":
                            userShopListMap.put("Thursday Lunch",itemList.getValue().toString());
                            break;
                        case "Friday Lunch":
                            userShopListMap.put("Friday Lunch",itemList.getValue().toString());
                            break;
                        case "Saturday Lunch":
                            userShopListMap.put("Saturday Lunch",itemList.getValue().toString());
                            break;
                        case "Sunday Lunch":
                            userShopListMap.put("Sunday Lunch",itemList.getValue().toString());
                            break;
                        case "Monday Dinner":
                            userShopListMap.put("Monday Dinner",itemList.getValue().toString());
                            break;
                        case "Tuesday Dinner":
                            userShopListMap.put("Tuesday Dinner",itemList.getValue().toString());
                            break;
                        case "Wednesday Dinner":
                            userShopListMap.put("Wednesday Dinner",itemList.getValue().toString());
                            break;
                        case "Thursday Dinner":
                            userShopListMap.put("Thursday Dinner",itemList.getValue().toString());
                            break;
                        case "Friday Dinner":
                            userShopListMap.put("Friday Dinner",itemList.getValue().toString());
                            break;
                        case "Saturday Dinner":
                            userShopListMap.put("Saturday Dinner",itemList.getValue().toString());
                            break;
                        case "Sunday Dinner":
                            userShopListMap.put("Sunday Dinner",itemList.getValue().toString());
                            break;
                        default:
                            // code block
                    }
                }
                displayToScreen(userShopListMap);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void displayToScreen(HashMap<String,String> userShopListMap){
        String value = userShopListMap.get("Monday BreakFast");
        mondayB.setText(value);
        mondayB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        value = userShopListMap.get("Tuesday BreakFast");
        tuesdayB.setText(value);
        tuesdayB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        value = userShopListMap.get("Wednesday BreakFast");
        wednesdayB.setText(value);
        wednesdayB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        value = userShopListMap.get("Thursday BreakFast");
        thursdayB.setText(value);
        thursdayB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        value = userShopListMap.get("Friday BreakFast");
        fridayB.setText(value);
        fridayB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        value = userShopListMap.get("Saturday BreakFast");
        saturdayB.setText(value);
        saturdayB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        value = userShopListMap.get("Sunday BreakFast");
        sundayB.setText(value);
        saturdayB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        value = userShopListMap.get("Monday Lunch");
        mondayL.setText(value);
        mondayL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        value = userShopListMap.get("Tuesday Lunch");
        tuesdayL.setText(value);
        tuesdayL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        value = userShopListMap.get("Wednesday Lunch");
        wednesdayL.setText(value);
        wednesdayL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        value = userShopListMap.get("Thursday Lunch");
        thursdayL.setText(value);
        thursdayL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        value = userShopListMap.get("Friday Lunch");
        fridayL.setText(value);
        fridayL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        value = userShopListMap.get("Saturday Lunch");
        saturdayL.setText(value);
        saturdayL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        value = userShopListMap.get("Sunday Lunch");
        sundayL.setText(value);
        saturdayL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        value = userShopListMap.get("Monday Dinner");
        mondayD.setText(value);
        mondayD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        value = userShopListMap.get("Tuesday Dinner");
        tuesdayD.setText(value);
        tuesdayD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        value = userShopListMap.get("Wednesday Dinner");
        wednesdayD.setText(value);
        wednesdayD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        value = userShopListMap.get("Thursday Dinner");
        thursdayD.setText(value);
        thursdayD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        value = userShopListMap.get("Friday Dinner");
        fridayD.setText(value);
        fridayD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        value = userShopListMap.get("Saturday Dinner");
        saturdayD.setText(value);
        saturdayD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        value = userShopListMap.get("Sunday Dinner");
        sundayD.setText(value);
        saturdayD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
