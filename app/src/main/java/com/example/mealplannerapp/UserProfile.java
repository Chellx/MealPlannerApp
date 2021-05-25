package com.example.mealplannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;

import android.icu.util.Calendar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class UserProfile extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private EditText userName,email;
    private TextView userDob;
    private Button addProfile,addDob;
    private DatabaseReference ref;
    private TextView comment;

    String mail="";
    String key="";
    boolean value =false;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mail = getIntent().getExtras().getString("email");
        key= mail.replace(".","");
        checkUser();

        setContentView(R.layout.activity_user_profile);

        email=findViewById(R.id.et_email);
        userName=findViewById(R.id.et_name);
        addProfile=findViewById(R.id.btn_addprofile);
        userDob = findViewById(R.id.et_DOB);
        comment=findViewById(R.id.et_nutritComment);

        mail= getIntent().getExtras().getString("email");
        email.setText(mail);

        findViewById(R.id.btn_adddob).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                showDatePicker();

            }
        });


        addProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = userName.getText().toString();
                String user_dob = userDob.getText().toString();

                if (user_name.isEmpty()) {
                    //Toast.makeText(UserProfile.this, "Empty", Toast.LENGTH_SHORT).show();
                } else{
                    addProfileToDatabase(user_name,user_dob);
                }
            }
        });
    }
    public void addProfileToDatabase(final String user_name,final String user_dob){

        ref = FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("User Profile").child(key).child("Account Type");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String v= snapshot.getValue().toString();
                HashMap<String,Object> userMap = new HashMap<>();
                userMap.put("Name", user_name);//key value
                userMap.put("DOB",user_dob);
                userMap.put("Account Type",v);
                FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("User Profile").child(key).updateChildren(userMap);
                Toast.makeText(UserProfile.this, "DATA ENTERED", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String date =  day + "/" + (month+1) + "/" + year;
        userDob.setText(date);

    }

    public void checkUser(){
        ref = FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("User Profile");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean value = false;
                String name = "";
                String date = "";

                for (DataSnapshot itemList: snapshot.getChildren()){
                    if(itemList.getKey().equals(key)){
                        for(DataSnapshot item: itemList.getChildren()){
                            if(item.getKey().equals("Name")){
                                userName.setText(item.getValue().toString());
                            }

                            else if(item.getKey().equals("DOB")) {
                                userDob.setText(item.getValue().toString());

                            }

                            else if(item.getKey().equals("Nutritionist Message")){
                                comment.setText(item.getValue().toString());
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