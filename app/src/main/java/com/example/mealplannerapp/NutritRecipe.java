package com.example.mealplannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NutritRecipe extends AppCompatActivity {

    String mail;
    TextView method,ingredient;
    Spinner recipeSpinner;
    DatabaseReference ref;
    Button recipeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrit_recipe);

        method=findViewById(R.id.et_recipeMethod);
        ingredient=findViewById(R.id.et_ingredients);
        recipeSpinner=(Spinner) findViewById(R.id.recipeList);

        getRecipeList();

        recipeButton=findViewById(R.id.btn_viewRecipe);

        recipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String recipe = recipeSpinner.getSelectedItem().toString();
                displayRecipeDetails(recipe);
            }
        });
    }

    public void getRecipeList(){
        ref = FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Recipes");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final List<String> recipes = new ArrayList<String>();

                for (DataSnapshot recList: snapshot.getChildren()){
                    for(DataSnapshot item: recList.getChildren()){
                        recipes.add(item.getKey().toString());
                    }
                }

                ArrayAdapter<String> recipeListAdapter = new ArrayAdapter<String>(NutritRecipe.this,android.R.layout.simple_spinner_item,recipes);
                recipeListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                recipeSpinner.setAdapter(recipeListAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void displayRecipeDetails(String recipe){
        ref = FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Recipes");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String key="";
                String value="";

                for(DataSnapshot recipeList: snapshot.getChildren()){
                    key=snapshot.getKey().toString();

                    for(DataSnapshot itemKey: recipeList.getChildren()){
                        value=itemKey.getKey().toString();

                        if(itemKey.getKey().equals(recipe)){
                            for(DataSnapshot item:itemKey.getChildren()){
                                value=itemKey.getKey().toString();

                                if(item.getKey().equals("Ingredients")){
                                    ingredient.setText(item.getValue().toString());
                                }

                                else if(item.getKey().equals("Method")){
                                    method.setText(item.getValue().toString());
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}