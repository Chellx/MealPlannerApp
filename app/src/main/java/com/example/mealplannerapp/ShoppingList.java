package com.example.mealplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class ShoppingList extends AppCompatActivity {

    private ListView userShoppingList;
    private EditText userItem;
    private Button addItem;
    private Button saveList;
    private  String shoppingItem;
    private ArrayAdapter<String> shoppingListArrayAdapter; // use to populate list view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        userShoppingList = findViewById(R.id.shoppingList);
        userItem = findViewById(R.id.shoppingItem);
        addItem = findViewById(R.id.addItemButton);
        saveList = findViewById(R.id.saveShoppingListButton);

        shoppingListArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        userShoppingList.setAdapter(shoppingListArrayAdapter);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoppingItem = userItem.getText().toString();
                shoppingListArrayAdapter.add(shoppingItem);
                shoppingListArrayAdapter.notifyDataSetChanged();
                userItem.setText("");
            }
        });

        saveList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String userShoppingListToSave = userItem.getText().toString();

                //String userShoppingListToSave = userItem.getText().toString();
                String userShoppingListToSave = "";
                //shoppingListArrayAdapter.add(shoppingItem);

                for(int i = 0;i <shoppingListArrayAdapter.getCount();i++){
                    userShoppingListToSave += shoppingListArrayAdapter.getItem(i) + ",";
                }
                if (shoppingItem.isEmpty()){
                    Toast.makeText(ShoppingList.this, "Shopping List Is Empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Shopping List").push().setValue(userShoppingListToSave);

                }
            }
        });


    }
}