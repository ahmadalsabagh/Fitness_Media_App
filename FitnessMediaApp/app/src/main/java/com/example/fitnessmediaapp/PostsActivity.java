package com.example.fitnessmediaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PostsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PostsAdapter postsRecyclerViewAdapter;

    @Override
    protected void onStart() {
        super.onStart();
        initRecyclerView();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts_recyclerview);
        recyclerView = findViewById(R.id.recyclerViewPosts);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(postsRecyclerViewAdapter != null){
            postsRecyclerViewAdapter.stopListening();
        }
//        snapShotActivationController.remove();
    }

    //----------ONCLICK for uploading posts
    public void uploadPost(View v){
//        final Map<String, Object> posts = new HashMap<>();
//        final EditText postContent = findViewById(R.id.txtPostContent);
//        postContentString = postContent.getText().toString();
//        posts.put("content", postContentString);
//
//        db.collection("posts")
//                .add(posts)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "Document added with ID" + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });
    }

    private void initRecyclerView(){

        Query query = FirebaseFirestore.getInstance()
                .collection("posts");

        FirestoreRecyclerOptions<Post> options = new FirestoreRecyclerOptions.Builder<Post>()
                .setQuery(query, Post.class)
                .build();

        postsRecyclerViewAdapter = new PostsAdapter(options);
        recyclerView.setAdapter(postsRecyclerViewAdapter);

        postsRecyclerViewAdapter.startListening();





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