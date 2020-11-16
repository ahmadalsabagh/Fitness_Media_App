package com.example.fitnessmediaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.LinkedList;
import java.util.List;

public class PostsActivity extends AppCompatActivity {
    private final LinkedList<String> mPostsList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private PostsAdapter mAdapter;
//    private TextView textViewData;
    public static final String Key = "post";

//    private FirebaseFirestore db = FirebaseFirestore.getInstance();
//    private DocumentReference postRef = db.document("posts/myPost");
    private static final String TAG = "PostsActivity";
    public static String postData;

//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        postRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
//                if (e != null) {
//                    //debug comment
//                    Toast.makeText(PostsActivity.this, "Error while loading!", Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, e.toString());
//                    return;
//                }
//                if (documentSnapshot.exists()) {
//                    postData = documentSnapshot.getString(Key);
//
//                }
//            }
//        });
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts_recyclerview);

        FirebaseFirestore.getInstance()
                .collection("posts")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentData, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e(TAG, "onEvent: ", error);
                            return;
                        }
                        if(queryDocumentData != null){
                            List<DocumentSnapshot> snapshotList = queryDocumentData.getDocuments();
                            for(DocumentSnapshot x : snapshotList){
                                Log.d(TAG, "onEvent: " + x.getData());
                            }
                        } else{
                            Log.e(TAG, "onEvent: query snapshot was null");
                        }
                    }
                });



//        Provides dummy data to linked list
//        for (int i = 0; i < 30; i++) {
//            mPostsList.addLast(postData);
//        }

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