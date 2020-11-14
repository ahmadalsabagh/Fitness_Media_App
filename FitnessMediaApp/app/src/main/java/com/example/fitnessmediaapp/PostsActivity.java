package com.example.fitnessmediaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.LinkedList;

public class PostsActivity extends AppCompatActivity {
    private final LinkedList<String> mPostsList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private PostsAdapter mAdapter;
//    private TextView textViewData;
    public static final String Key = "post";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference postRef = db.document("posts/myPost");
    private static final String TAG = "PostsActivity";
    public static String postData;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts_recyclerview);

        postRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            // we are taking the information from database, storing it into a string, then adding it to a linked list, so
                            // posts adapter can display the data on the screen.
                            postData = documentSnapshot.getString(Key);
                            Log.d(TAG, "onSuccess: retrieving data");
                            //Map<String, Object> note = documentSnapshot.getData();
//                            textViewData.setText("Title: " + postData);
                        } else {
                            Log.d(TAG, "onFailure: Document Doesn't exist");

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PostsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });



        for (int i = 0; i < 30; i++) {
            mPostsList.addLast(postData);
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
        Intent intent = new Intent(this, PostsActivity.class);
        startActivity(intent);
    }

    //Onclick functions for the menu bar
    public void goToAccountSettings(View v){
        Intent intent = new Intent(this, AccountSettings.class);
        startActivity(intent);
    }
}