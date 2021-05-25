package com.example.mealplannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class NutritProfile extends AppCompatActivity {

    String mail = "";
    String key = "";
    EditText email,name,regNumber, phone;
    Button addButton;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrit_profile);

        mail = getIntent().getExtras().getString("email");
        key = mail.replace(".","");

        email=findViewById(R.id.et_email);
        name=findViewById(R.id.et_name);
        regNumber=findViewById(R.id.et_regNum);
        phone=findViewById(R.id.et_phoneNum);
        addButton=findViewById(R.id.btn_addprofile);

        email.setText(mail);
        checkUser();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=name.getText().toString();
                String reg=regNumber.getText().toString();
                String p=phone.getText().toString();
                addDetails(n,reg,p);
            }
        });
    }

    public void addDetails(String user_name,String regNum,String phone){
        ref= FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("User Profile").child(key).child("Account Type");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String type = snapshot.getValue().toString();
                HashMap<String,Object> userMap = new HashMap<>();
                userMap.put("Name", user_name); //key value
                userMap.put("Registration Number", regNum);
                userMap.put("Phone Number", phone);
                userMap.put("Account Type", type);

                FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("User Profile").child(key).updateChildren(userMap);
                Toast.makeText(NutritProfile.this,"DATA ENTERED",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void checkUser(){
        ref= FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("User Profile").child(key);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String key = snapshot.getKey();
                for(DataSnapshot itemList: snapshot.getChildren()){
                    key = itemList.getKey();
                    if(key.equals("Name")){
                        name.setText(itemList.getValue().toString());
                    }
                    else if(key.equals("Phone Number")){
                        phone.setText(itemList.getValue().toString());
                    }
                    else if(key.equals("Registration Number")){
                        regNumber.setText(itemList.getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}