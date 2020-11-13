package com.example.fitnessmediaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class Posts extends AppCompatActivity {
    private final LinkedList<String> mPostsList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private PostsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_recyclerview);

        for (int i = 0; i < 30; i++) {
            mPostsList.addLast("test " + i);
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new PostsAdapter(this, mPostsList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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