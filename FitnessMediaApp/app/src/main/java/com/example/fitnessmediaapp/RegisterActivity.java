package com.example.fitnessmediaapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    public static final String FirstN_Key = "firstName";
    public static final String LastN_Key = "lastName";
    public static final String UserN_Key = "username";
    public static final String Password_Key = "password";
    public static final String Authorization_Key = "authorizationLevel";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static String firstNameString;
    public static String lastNameString;
    public static String userNameString;
    public static String passwordString;
    public static String authorizationLevel;
    private ListenerRegistration userSnapshotListener;
    public boolean validUsername = true;
    public static String usernameFromDatabase;
    public static String getID;

    @Override
    protected void onStart() {
        super.onStart();
        final Map<String, Object> user = new HashMap<>();
        validUsername = true;
        final EditText firstName = findViewById(R.id.firstNameTxt);
        final EditText lastName = findViewById(R.id.lastNameTxt);
        final EditText userName = findViewById(R.id.userNameTxt);
        final EditText password = findViewById(R.id.passwordTxt);
        Button btnRegisterUser = findViewById(R.id.createUserBtn);

        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastNameString = lastName.getText().toString();
                firstNameString = firstName.getText().toString();
                userNameString = userName.getText().toString();
                passwordString = password.getText().toString();
                authorizationLevel = "0";
                validUsername = true;

                userSnapshotListener = FirebaseFirestore.getInstance()
                        .collection("users")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                             @Override
                             public void onEvent(@Nullable QuerySnapshot queryDocumentData, @Nullable FirebaseFirestoreException error) {
                                 if (error != null) {
                                     Log.e(TAG, "onEvent: ", error);
                                     return;
                                 }
                                 if (queryDocumentData != null) {
                                     List<DocumentSnapshot> snapshotList = queryDocumentData.getDocuments();
                                     for (DocumentSnapshot x : snapshotList) {
                                         usernameFromDatabase = x.getString("username");
                                         getID = x.getId();
                                         System.out.println("The document reference is: " + getID);
                                         System.out.println("Username input" + userNameString);
                                         System.out.println("Username From Database" + usernameFromDatabase);
                                         if(usernameFromDatabase.equals(userNameString) == true){
                                             validUsername = false;

//                                             System.out.println("---------------------------");
//                                             System.out.println("----------------------------");
//                                             System.out.println("----------------------------");
//                                             System.out.println("we have a duplicate username!!!!");
                                         }
                                     }
                                 }
                             }
                        });


//                CountDownTimer timer = new CountDownTimer(1000, 250) {
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//                        System.out.println("Username pending for 1s");
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        user.put(FirstN_Key, firstNameString);
//                        user.put(LastN_Key, lastNameString);
//                        user.put(UserN_Key, userNameString);
//                        user.put(Password_Key, passwordString);
//                        user.put(Authorization_Key, authorizationLevel);
//
//                        if (lastNameString.equals("") == true || firstNameString.equals("") == true ||
//                                userNameString.equals("") == true || passwordString.equals("") == true) {
//                            Toast toast = Toast.makeText(getApplicationContext(), "Please fill out all fields!",
//                                    Toast.LENGTH_SHORT);
//                            toast.show();
//                        }
//                        if(validUsername == false){
//                            System.out.println("Username not looking good");
//                            Toast toast = Toast.makeText(getApplicationContext(), "Invalid Username",
//                                    Toast.LENGTH_SHORT);
//                            toast.show();
//                        }
//                        if(validUsername == true){
//                            System.out.println("Username Looks fine");
////                            Toast toast = Toast.makeText(getApplicationContext(), "Username is good",
////                                    Toast.LENGTH_SHORT);
////                            toast.show();
//                            db.collection("users")
//                                    .add(user)
//                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                        @Override
//                                        public void onSuccess(DocumentReference documentReference) {
//                                            Log.d(TAG, "Document added with ID" + documentReference.getId());
//                                        }
//                                    })
//                                    .addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Log.w(TAG, "Error adding document", e);
//                                        }
//                                    });
//                            Intent intent = new Intent(getApplicationContext(), OpeningScreen.class);
//                            startActivity(intent);
//                        }
//                    }
//                };
//
//                timer.start();







                //btn click listener and onstart
                    }
                });
            }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//         Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Register");  // provide compatibility to all the versions
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        userSnapshotListener.remove();
    }
}