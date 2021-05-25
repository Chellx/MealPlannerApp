package com.example.mealplannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NutritPatients extends AppCompatActivity {
    String mail ="";
    Spinner patientSpinner;
    EditText message;
    Button saveButton,mealPlans;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrit_patients);
        mail=getIntent().getExtras().getString("email");
        message=findViewById(R.id.et_commentToPatient);
        patientSpinner=(Spinner) findViewById(R.id.patientList);
        getPatientRecords();

        saveButton =findViewById(R.id.btn_addprofile);
        mealPlans=findViewById(R.id.btn_viewMealPlans);

        mealPlans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = patientSpinner.getSelectedItem().toString();
                Bundle bundle = new Bundle();
                bundle.putString("email",userId);
                bundle.putString("bool","1");
                Intent intent = new Intent(NutritPatients.this,CurrentMealPlan.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = patientSpinner.getSelectedItem().toString();
                String patientMessage = message.getText().toString();
                sendMessageToPatient(text,patientMessage);
            }
        });
    }

    public void getPatientRecords(){
        ref = FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("User Profile");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final List<String> patient=new ArrayList<String>();

                for(DataSnapshot IdList: snapshot.getChildren()){
                    String accountId = IdList.getKey();

                    for(DataSnapshot item: IdList.getChildren()){
                        String value = item.getValue().toString();

                        if(value.equals("user")){
                            patient.add(accountId);
                        }
                    }
                }

                ArrayAdapter<String> patientListAdapter = new ArrayAdapter<String>(NutritPatients.this,android.R.layout.simple_spinner_item,patient);
                patientListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                patientSpinner.setAdapter(patientListAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void sendMessageToPatient(String email,String newMessage){
        ref = FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("User Profile").child(email);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String key = snapshot.getValue().toString();
                HashMap<String,Object> userMap = new HashMap<>();

                for(DataSnapshot items: snapshot.getChildren()){

                    if(key.equals("Name")){
                        userMap.put("Name", items.getValue().toString());
                    }

                    else if(key.equals("DOB")){
                        userMap.put("DOB",items.getValue().toString());
                    }

                    else if(key.equals("Account Type")){
                        userMap.put("Account Type",items.getValue().toString());
                    }
                }

                userMap.put("Nutritionist Message", newMessage);
                FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("User Profile").child(email).updateChildren(userMap);
                Toast.makeText(NutritPatients.this,"Message Sent",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}