package com.example.mealplannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity  {
    private EditText email;
    private EditText password;
    private Button register;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=findViewById(R.id.et_email);
        password=findViewById(R.id.et_password);
        register=findViewById(R.id.btn_register);

        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = email.getText().toString();
                String userPass = password.getText().toString();

                if(TextUtils.isEmpty(userEmail)||TextUtils.isEmpty(userPass)){
                    Toast.makeText(MainActivity.this,"Empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    registerUser(userEmail,userPass);
                }
            }
        });


    }

    private void registerUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"SUCCESS",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,UserHomePage.class));
                }
                else{
                    Toast.makeText(MainActivity.this,"FAIL",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
