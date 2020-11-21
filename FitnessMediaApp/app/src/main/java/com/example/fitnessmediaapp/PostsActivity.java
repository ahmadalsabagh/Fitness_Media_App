package com.example.fitnessmediaapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
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
import java.util.Locale;
import java.util.Map;

public class PostsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PostsAdapter postsRecyclerViewAdapter;



    private FirebaseFirestore db = FirebaseFirestore.getInstance();
//    private DocumentReference postRef = db.document("posts/myPost");
    private static final String TAG = "PostsActivity";
    public static String postContent;
    public static String postLocation;
    public static String postTime;
    public static String postUsername;
    public static String postContentString;
    //    public static String toast_message = "heloooooooooooo";
    //Registering the listener
    private ListenerRegistration snapShotActivationController;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onStart() {
        super.onStart();
        initRecyclerView();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts_recyclerview);
        recyclerView = findViewById(R.id.recyclerViewPosts);

        Button btnPost = findViewById(R.id.createPostBtn);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getCoordinates();
                showAlertDialog();
//                final Map<String, Object> posts = new HashMap<>();
//                TextView postContentToUpload = findViewById(R.id.txtPostContent);
//                postContentString = postContentToUpload.getText().toString();
//                posts.put("content", postContentString);
//
//                db.collection("posts")
//                        .add(posts)
//                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                            @Override
//                            public void onSuccess(DocumentReference documentReference) {
//                                Log.d(TAG, "Document added with ID" + documentReference.getId());
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.w(TAG, "Error adding document", e);
//                            }
//                        });
            }
        });


    }

    private void showAlertDialog() {
        final EditText postEditText = new EditText(this);
        new AlertDialog.Builder(this)
                .setTitle("Make a New Fitness Post!")
                .setView(postEditText)
                .setPositiveButton("Post", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "onClick: " + postEditText.getText());
                        final Map<String, Object> posts = new HashMap<>();
                posts.put("content", postEditText.getText().toString());

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
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(postsRecyclerViewAdapter != null){
            postsRecyclerViewAdapter.stopListening();
        }
    }

    //----------ONCLICK for uploading posts
//    public void uploadPost(View v){
//        setContentView(R.layout.posts_recyclerview);
//        final Map<String, Object> posts = new HashMap<>();
//        EditText postContentToUpload = findViewById(R.id.txtPostContent);
//        postContentString = postContentToUpload.getText().toString();
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
//
//    }

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

    public void getCoordinates(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                //get location
                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>(){
                    public void onSuccess(Location location){
                        if(location != null){

                            Double lat = location.getLatitude();
                            Double lon = location.getLongitude();
                            getLocationFromCoordinates(lat,lon);
                        }
                    }
                });
            }else{
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }
    }

    public void getLocationFromCoordinates(Double lat, Double lon){
        try {
            Geocoder gcd = new Geocoder(this.getApplicationContext(), Locale.getDefault());
            List<Address> addresses = gcd.getFromLocation(lat, lon, 1);

            if (addresses.size() > 0) {
                String countryName = addresses.get(0).getCountryName();

                //Set location textview to countryName!!!
                Toast.makeText(this, countryName, Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Toast.makeText(this, "No Location Name Found", Toast.LENGTH_LONG).show();
        }
    }

}