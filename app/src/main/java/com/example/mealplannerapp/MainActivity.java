package com.example.mealplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText userName,userEmail,userPassword;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        userName= (EditText) findViewById(R.id.et_username);
        userEmail= (EditText) findViewById(R.id.et_email);
        userPassword= (EditText) findViewById(R.id.et_password);
        registerButton = (Button) findViewById(R.id.btn_register);
        AddData();

    }

    public void AddData (){
        registerButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                      boolean isInserted =  myDb.insertData(userName.getText().toString(),
                                userEmail.getText().toString(),
                                userPassword.getText().toString());
                      if(isInserted = true)
                          Toast.makeText(MainActivity.this,"User Data Inserted", Toast.LENGTH_LONG).show();
                      else
                          Toast.makeText(MainActivity.this,"User Data NOT Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}