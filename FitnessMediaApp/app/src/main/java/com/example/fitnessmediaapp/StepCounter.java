package com.example.fitnessmediaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class StepCounter extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_count);
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
        Intent intent = new Intent(this, Posts.class);
        startActivity(intent);
    }

    //Onclick functions for the menu bar
    public void goToAccountSettings(View v){
        Intent intent = new Intent(this, AccountSettings.class);
        startActivity(intent);
    }
}
