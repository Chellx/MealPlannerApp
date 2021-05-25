package com.example.mealplannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginScreen extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button login;
    private DatabaseReference ref;
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

                if(TextUtils.isEmpty(userEmail)||TextUtils.isEmpty(userPass)){
                    Toast.makeText(LoginScreen.this,"EMPTY FIELDS",Toast.LENGTH_SHORT).show();
                }
                else {
                    loginUser(userEmail, userPass);
                }

            }
        });


    }

    private void loginUser(String email, String pass) {
        final String mail = email;
        final String key = email.replace(".","");
        auth.signInWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                //Toast.makeText(LoginScreen.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                ref = FirebaseDatabase.getInstance("https://mealplannerapp-a2bb5-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("User Profile").child(key).child("Account Type");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String accountType = snapshot.getValue().toString();
                        if(accountType.equals("user")){
                            Bundle bundle = new Bundle();
                            bundle.putString("email",mail);
                            Intent intent = new Intent (LoginScreen.this,UserHomePage.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }

                        else if(accountType.equals("admin")){
                            Bundle bundle = new Bundle();
                            bundle.putString("email",mail);
                           // Intent intent = new Intent (LoginScreen.this,UserHomePage.class);
                          //  intent.putExtras(bundle);
                           // startActivity(intent);

                        }
                        else{
                            Bundle bundle = new Bundle();
                            bundle.putString("email",mail);
                            Intent intent = new Intent (LoginScreen.this,NutritHomePage.class);
                             intent.putExtras(bundle);
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
    }
}