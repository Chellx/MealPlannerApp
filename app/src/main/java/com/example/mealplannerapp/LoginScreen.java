package com.example.mealplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button login;

    private CheckBox nutrit,admin,user;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        email=findViewById(R.id.et_email);
        password=findViewById(R.id.et_password);
        login=findViewById(R.id.btn_login);

        auth = FirebaseAuth.getInstance();


        nutrit =findViewById(R.id.check_nutrit);
        admin=findViewById(R.id.check_admin);
        user=findViewById(R.id.check_user);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail=email.getText().toString();
                String userPass=password.getText().toString();
                loginUser(userEmail,userPass);

            }
        });


    }

    private void loginUser(String email, String pass) {
        final String mail = email;
        auth.signInWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(LoginScreen.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putString("email", mail);
                Intent intent = new Intent(LoginScreen.this,UserHomePage.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
}