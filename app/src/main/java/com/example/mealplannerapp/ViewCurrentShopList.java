package com.example.mealplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewCurrentShopList extends AppCompatActivity {

    private ListView listView;
    private EditText shopListName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_current_shop_list);

        listView = findViewById(R.id.currentShoppingList);
        shopListName = findViewById(R.id.et_currentListName);

        ArrayList<String> shopList = new ArrayList<>();
        ArrayAdapter shopListAdapter = new ArrayAdapter<String>(this, R.layout.list_item,shopList);
        listView.setAdapter(shopListAdapter);

        DatabaseReference reference = FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Shopping List");


    }

    public void goBack (View view){
        // Do something in response to button
        Intent intent = new Intent (this, ShoppingHomePage.class);
        startActivity(intent);
    }
}