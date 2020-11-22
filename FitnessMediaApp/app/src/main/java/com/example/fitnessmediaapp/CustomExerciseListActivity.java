package com.example.fitnessmediaapp;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
//import com.google.android.gms.location.LocationServices;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class CustomExerciseListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomExerciseListAdapter viewAdapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onStart() {
        super.onStart();
        initRecyclerView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customexercise_recyclerview);
        recyclerView = findViewById(R.id.recyclerViewCustomList);
        System.out.println("inside of CustomExerciseList");
        //Show the Up button in the action bar.
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setTitle("Custom Exercise List");  // provide compatibility to all the versions
//        }
    }

    private void initRecyclerView(){
        Query query = FirebaseFirestore.getInstance()
                .collection("customExerciseList");

        FirestoreRecyclerOptions<CustomExerciseList> options = new FirestoreRecyclerOptions.Builder<CustomExerciseList>()
                .setQuery(query, CustomExerciseList.class)
                .build();

        viewAdapter = new CustomExerciseListAdapter(options);
        recyclerView.setAdapter(viewAdapter);
        viewAdapter.startListening();
    }
}
