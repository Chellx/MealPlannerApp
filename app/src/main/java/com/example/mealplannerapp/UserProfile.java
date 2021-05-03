package com.example.mealplannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UserProfile extends AppCompatActivity {

    private EditText userName;
    private Button addProfile;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userName=findViewById(R.id.et_name);
        addProfile=findViewById(R.id.btn_addprofile);




       addProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //rootNode = FirebaseDatabase.getInstance();
                //reference = rootNode.getReference("users");
                //reference.setValue("First Data");
                String user_name = userName.getText().toString();

               if(user_name.isEmpty()){
                    Toast.makeText(UserProfile.this,"Empty",Toast.LENGTH_SHORT).show();
                }
                else{

                   // reference.setValue("test");
                    FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("User Profile").push().child("Name").setValue(user_name);
                   Toast.makeText(UserProfile.this,"DATA ENTERED",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}