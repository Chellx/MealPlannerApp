package com.example.mealplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText userName,userPassword;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        myDb = new DatabaseHelper(this);
        userName= (EditText) findViewById(R.id.et_username);
        userPassword= (EditText) findViewById(R.id.et_password);
        loginButton = (Button) findViewById(R.id.btn_login);
        SelectData();
    }

    public void SelectData (){
        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       String user = userName.getText().toString();
                       String password = userPassword.getText().toString();
                       Cursor cursor = myDb.getData(user,password);
                        if(cursor.getCount() != 0 )
                            Toast.makeText(LoginScreen.this,"Data Found", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(LoginScreen.this,"Data not found", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}