package com.example.mealplannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity  {
    private EditText email;
    private EditText password;
    private Button register;
    private String accountType = null;
    private FirebaseAuth auth;
    boolean valid = true;
    private CheckBox nutrit,admin,user;
    private DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=findViewById(R.id.et_email);
        password=findViewById(R.id.et_password);
        register=findViewById(R.id.btn_register);

        auth = FirebaseAuth.getInstance();

        nutrit =( CheckBox ) findViewById(R.id.check_nutrit);
        admin=( CheckBox ) findViewById(R.id.check_admin);
        user=( CheckBox ) findViewById(R.id.check_user);


        

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = email.getText().toString();
                String userPass = password.getText().toString();

                if(TextUtils.isEmpty(userEmail)||TextUtils.isEmpty(userPass)){
                    Toast.makeText(MainActivity.this,"EMPTY FIELDS",Toast.LENGTH_SHORT).show();
                }
                else{
                    registerUser(userEmail,userPass,accountType);
                }
            }
        });

        nutrit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                admin.setChecked(false);
                user.setChecked(false);

                if(isChecked){
                    accountType="nutritionist";

                }

            }
        });

        admin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                nutrit.setChecked(false);
                user.setChecked(false);

                if (isChecked) {
                    accountType="admin";

                }
            }
        });

        user.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                admin.setChecked(false);
                nutrit.setChecked(false);

                if (isChecked) {
                    accountType="user";


                }
            }
        });
        }






    private void registerUser(String email, String password, final String accountType) {
        if(accountType!=null) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        setAccountType(accountType);
                    }
                    //finish(); not allow user to go back
                    else {
                        Toast.makeText(MainActivity.this, "ERROR FAILED TO CREATE ACCOUNT", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        else{
            Toast.makeText(MainActivity.this, "ACCOUNT TYPE REQUIRED", Toast.LENGTH_SHORT).show();
        }
    }

    public void setAccountType(String accountT){
        String mail = email.getText().toString();
        mail=mail.replace(".","");
        HashMap<String,Object> accountMap = new HashMap<>();
        accountMap.put("Account Type",accountT);
        FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("User Profile").child(mail).setValue(accountMap);
        Toast.makeText(MainActivity.this,"REGISTRATION SUCCESSFUL",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this,LoginAndRegister.class));

    }
}
