package com.example.mealplannerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mealplanner.db"; //Database name
    public static final String TABLE_NAME = "user_register_table"; //table name
    //columns
    public static final String COL_1 = "ID"; //table name
    public static final String COL_2 = "user_name";
    public static final String COL_3 = "user_email";
    public static final String COL_4 = "user_password";



    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME , null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, user_name TEXT, user_email TEXT, user_password TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME );
        onCreate(sqLiteDatabase);

    }

    public boolean insertData (String user_name, String user_email, String user_password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, user_name);
        contentValues.put(COL_3, user_email);
        contentValues.put(COL_4, user_password);
        long result = sqLiteDatabase.insert(TABLE_NAME,null, contentValues);
        if (result==-1)
            return false;
            else
                return true;

    }
    public Cursor getData(String userName, String userPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor res =  db.rawQuery( "select * from "+ TABLE_NAME + " Where user_name" + "=" + userName, null );
        Cursor c = db.query(TABLE_NAME, null, COL_2 + "=?"+" AND "+COL_4 + "=?" ,
                new String[] { userName,userPassword  },null, null, null, null);
        return c;
    }
}

