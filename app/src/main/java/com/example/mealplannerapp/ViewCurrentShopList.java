package com.example.mealplannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewCurrentShopList extends AppCompatActivity {

    private EditText shopListName,displayList;
    //__________________________________
    private String email = "";
    private String itemList = "";
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_current_shop_list);
        displayList = findViewById(R.id.et_hidden);
        //__________________________________________________________
        email = getIntent().getExtras().getString("email");
        email=email.replace(".","");
        //___________________________________________________________
        getData();
        shopListName = findViewById(R.id.et_currentListName);
    }



    public void getData(){
        final String TAG = "";
        ref = FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Shopping List");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean value = false;
                String listName = "";
                String items = "";

                for(DataSnapshot itemList: snapshot.getChildren()){
                    if(itemList.getKey().equals(email))
                    {
                        for(DataSnapshot item: itemList.getChildren()){
                            if(value){
                                listName = item.getValue().toString();
                                shopListName.setText(listName);
                            }
                            else{
                                items = item.getValue().toString();
                                items=items.replace(",","\n");
                                displayList.setText(items);
                                value=true;
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

    public void goBackShop (View view){
        // Do something in response to button
        Intent intent = new Intent (this, UserHomePage.class);
        startActivity(intent);
    }



}