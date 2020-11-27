package com.example.fitnessmediaapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
//    DatabaseHelper myDB;
//    public static String databaseInfo;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        myDB = new DatabaseHelper(this);
//        boolean delete = myDB.deleteData();
//        boolean newUser = myDB.createUser("Daniel", "Lodge", "123", "dlodge");
//        System.out.println("Status: " + newUser);
//        Cursor data = myDB.getData();
//
//
//
//
//        ArrayList<String> listData = new ArrayList<>();
//        while(data.moveToNext()){
//            listData.add(data.getString(4));
//        }
//
//        System.out.println("the List data:" + listData.toString());

        /*
        The docs for SQLiteDatabase.query() say that the query methods return:

"A Cursor object, which is positioned before the first entry."

Calling moveToFirst() does two things: it allows you to test whether
 the query returned an empty set (by testing the return value) and it moves the
  cursor to the first result (when the set is not empty). Note that to guard against an empty return set, the code you posted should be testing the return value (which it is not doing).
         */







    }

    //Onclick functions for the menu bar
    public void goToStepCount(View v){
        Intent intent = new Intent(this, StepCounter.class);
        startActivity(intent);
    }

    //Onclick functions for the menu bar
    public void goToExerciseList(View v){
        Intent intent = new Intent(this, ExerciseListMain.class);
        startActivity(intent);
    }

    //Onclick functions for the menu bar
    public void goToPosts(View v){
        Intent intent = new Intent(this, PostsActivity.class);
        startActivity(intent);
    }

    //Onclick functions for the menu bar
    public void goToAccountSettings(View v){
        Intent intent = new Intent(this, AccountSettings.class);
        startActivity(intent);
    }
}
