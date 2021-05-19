package com.example.mealplannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MealRecipeView extends AppCompatActivity {
    private String email = "";
    private String mealName = "";
    private String mealType = "";
    private Button recipeName;
    private TextView recipeMethod,ingredients;
    private DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_recipe_view);
        mealName = getIntent().getExtras().getString("meal");
        mealType = getIntent().getExtras().getString("mealType");
        recipeName = findViewById(R.id.btn_recipeName);
        ingredients = findViewById(R.id.ingredients);
        recipeMethod = findViewById(R.id.method);
        recipeName.setText(mealName);

        ingredients.setMovementMethod(new ScrollingMovementMethod());
        recipeMethod.setMovementMethod(new ScrollingMovementMethod());

        getIngredientAndMethod();

    }

    public void getIngredientAndMethod() {
        ref = FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Recipes").child(mealType).child(mealName);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String key = snapshot.getKey();
                boolean value = false;
                String listName = "";
                String items = "";
                for(DataSnapshot itemList: snapshot.getChildren()){
                    if(value){
                        listName = itemList.getValue().toString();

                        ingredients.setText(listName);
                    }
                    else{
                        items = itemList.getValue().toString();
                        recipeMethod.setText(items);
                        value=true;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void gobackHome (View view){
        // Do something in response to button
        Intent intent = new Intent (this, UserHomePage.class);
        startActivity(intent);
    }


}