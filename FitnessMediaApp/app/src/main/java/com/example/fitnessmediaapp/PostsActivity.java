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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PostsActivity extends AppCompatActivity {
    private final LinkedList<Post> mPostsList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private PostsAdapter mAdapter;
//    private TextView textViewData;
    public static final String Key = "post";

//    private FirebaseFirestore db = FirebaseFirestore.getInstance();
//    private DocumentReference postRef = db.document("posts/myPost");
    private static final String TAG = "PostsActivity";
    public static String postContent;
    public static String postLocation;
    public static String postTime;
    public static String postUsername;
    public static String postContentString;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
//    public static String toast_message = "heloooooooooooo";
    //Registering the listener
    private ListenerRegistration snapShotActivationController;


    @Override
    protected void onStart() {
        super.onStart();

        snapShotActivationController = FirebaseFirestore.getInstance()
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
                                postContent = x.getString("content");
                                postLocation = x.getString("location");
                                postTime = x.getString("time");
                                postUsername = x.getString("username");

                                Post postData = new Post(postContent, postLocation, postTime, postUsername);

                                mPostsList.addLast(postData);
                            }
                        } else{
                            Log.e(TAG, "onEvent: query snapshot was null");
                        }
                    }
                });

        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new PostsAdapter(this, mPostsList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts_recyclerview);

//        FirebaseFirestore.getInstance()
//                .collection("posts")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot queryDocumentData, @Nullable FirebaseFirestoreException error) {
//                        if(error != null){
//                            Log.e(TAG, "onEvent: ", error);
//                            return;
//                        }
//                        if(queryDocumentData != null){
//                            List<DocumentSnapshot> snapshotList = queryDocumentData.getDocuments();
//                            for(DocumentSnapshot x : snapshotList){
//                                postContent = x.getString("content");
//                                postLocation = x.getString("location");
//                                postTime = x.getString("time");
//                                postUsername = x.getString("username");
//
//                                Post postData = new Post(postContent, postLocation, postTime, postUsername);
//
//                                mPostsList.addLast(postData);
//                            }
//                        } else{
//                            Log.e(TAG, "onEvent: query snapshot was null");
//                        }
//                    }
//                });



//        Provides dummy data to linked list
//        for (int i = 0; i < 30; i++) {
//
//        }


    }

    @Override
    protected void onStop() {
        super.onStop();
//        snapShotActivationController.remove();
    }

    //----------ONCLICK for uploading posts
    public void uploadPost(View v){
        final Map<String, Object> posts = new HashMap<>();
        final EditText postContent = findViewById(R.id.txtPostContent);
        postContentString = postContent.getText().toString();
        posts.put("content", postContentString);

        db.collection("posts")
                .add(posts)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Document added with ID" + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
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