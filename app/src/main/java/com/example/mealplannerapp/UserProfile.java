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

    private EditText userName;
    private TextView userDob;
    private Button addProfile,addDob;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userName=findViewById(R.id.et_name);
        addProfile=findViewById(R.id.btn_addprofile);
        userDob = findViewById(R.id.et_DOB);

        findViewById(R.id.btn_adddob).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                showDatePicker();

            }
        });


                addProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //rootNode = FirebaseDatabase.getInstance();
                        //reference = rootNode.getReference("users");
                        //reference.setValue("First Data");
                        String user_name = userName.getText().toString();
                        String user_dob = userDob.getText().toString();

                        if (user_name.isEmpty()) {
                            Toast.makeText(UserProfile.this, "Empty", Toast.LENGTH_SHORT).show();
                        } else {

                            // reference.setValue("test");

                            HashMap<String,Object> userMap = new HashMap<>();
                            userMap.put("Name", user_name);//key value
                            userMap.put("DOB",user_dob);
                            FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("User Profile").push().setValue(userMap);
                           // FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("User Profile").push().child("DOB").setValue(user_dob);
                            Toast.makeText(UserProfile.this, "DATA ENTERED", Toast.LENGTH_SHORT).show();
                        }
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


}