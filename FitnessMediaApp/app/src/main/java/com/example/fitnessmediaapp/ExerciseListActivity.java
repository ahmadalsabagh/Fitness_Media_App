package com.example.fitnessmediaapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ExerciseListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exerciselist_detail);
        //Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Details");  // provide compatibility to all the versions
        }
        getIncomingIntent();
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("exercise_description")) {
            String exerciseDescription = getIntent().getStringExtra("exercise_description");
            setDescription(exerciseDescription);
        }
    }

    private void setDescription(String exerciseDescription) {
        TextView recipeText = findViewById(R.id.exercise_description);
        recipeText.setText(exerciseDescription);
    }
}
