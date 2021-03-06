package com.example.fitnessmediaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.LinkedList;

public class ExerciseListMain extends AppCompatActivity {
    private final LinkedList<String> mExerciseList = new LinkedList<>();
    private final LinkedList<String> mDescriptionList = new LinkedList<>();
    private final LinkedList<Integer> mImageList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private ExerciseListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_list_recyclerview);
        Button showList = findViewById(R.id.goToCustomList);


        mExerciseList.add("Dumbbell Fly");
        mDescriptionList.add("An exercise that focuses on the chest");
        mImageList.add(R.drawable.dfly);

        mExerciseList.add("Chest Press");
        mDescriptionList.add("An exercise that focuses on the chest");
        mImageList.add(R.drawable.cpress);

        mExerciseList.add("Reverse Fly");
        mDescriptionList.add("An exercise that focuses on the rear delts");
        mImageList.add(R.drawable.rfly);

        mExerciseList.add("Shoulder press");
        mDescriptionList.add("An exercise that focuses on the front delts");
        mImageList.add(R.drawable.spress);

        mExerciseList.add("Lateral raises");
        mDescriptionList.add("An exercise that focuses on the side delts");
        mImageList.add(R.drawable.lateral);

        mExerciseList.add("Front raises");
        mDescriptionList.add("An exercise that focuses on the front delts");
        mImageList.add(R.drawable.fraises);



        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerview2);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new ExerciseListAdapter(this, mExerciseList, mDescriptionList,mImageList);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        showList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseListMain.this, CustomExerciseListActivity.class);
                startActivity(intent);
            }
        });

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