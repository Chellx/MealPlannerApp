package com.example.mealplannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ShoppingList extends AppCompatActivity {

    private ListView userShoppingList;
    private EditText userItem;
    private Button addItem;
    private Button saveList;
    private String shoppingItem;
    private ArrayAdapter<String> shoppingListArrayAdapter; // use to populate list view
    private EditText listName;

    DatabaseReference ref;
    String email= "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},PackageManager.PERMISSION_GRANTED); //grant permission for user to use camera to scan

        userShoppingList = findViewById(R.id.shoppingList);
        userItem = findViewById(R.id.shoppingItem);
        addItem = findViewById(R.id.addItemButton);
        saveList = findViewById(R.id.saveShoppingListButton);
        listName = findViewById(R.id.et_listName);


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

                //shoppingListArrayAdapter.add(shoppingItem);
                //String userShoppingListToSave = "";

                String userShoppingListToSave = userItem.getText().toString();
                String userListName = listName.getText().toString();

                email = getIntent().getExtras().getString("email");
                email=email.replace(".","");


                for(int i = 0;i <shoppingListArrayAdapter.getCount();i++){
                    userShoppingListToSave += shoppingListArrayAdapter.getItem(i) + ",";
                    //userShoppingListToSave += shoppingListArrayAdapter.getItem(i);

                }
                if (shoppingItem.isEmpty()){
                    //Toast.makeText(ShoppingList.this, "Shopping List Is Empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    HashMap<String,Object> userShopListMap = new HashMap<>();
                    userShopListMap.put("Shopping List Name", userListName);//key value
                    userShopListMap.put("Items",userShoppingListToSave);
                    FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Shopping List").child(email).setValue(userShopListMap);
                }
            }
        });

    }
}