package com.example.fitnessmediaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class AccountSettings extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    public static final String FirstN_Key = "firstName";
    public static final String LastN_Key = "lastName";
    public static final String UserN_Key = "username";
    public static final String Password_Key = "password";
//    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static String firstNameString;
    public static String lastNameString;
    public static String userNameString;
    public static String passwordString;
    private ListenerRegistration userSnapshotListener;
    public boolean validUsername = true;
    public static String usernameFromDatabase;
    public static String authorizationLevelFromDatabase;
    public static String authorizedUsername;
    public static String authorizedUserId;





    @Override
    protected void onStart() {
        super.onStart();
        final Map<String, Object> user = new HashMap<>();
        final EditText firstName = findViewById(R.id.newFirstNameTxt);
        final EditText lastName = findViewById(R.id.newLastNameTxt);
        final EditText userName = findViewById(R.id.newUsernameTxt);
        final EditText password = findViewById(R.id.newPasswordTxt);
        Button btnUpdateUsername = findViewById(R.id.updateUsernameBtn);
        Button btnUpdatePassword = findViewById(R.id.updatePasswordBtn);
        Button btnUpdateFirstName = findViewById(R.id.updateFirstNameBtn);
        Button btnUpdateLastName = findViewById(R.id.updateLastNameBtn);

        btnUpdateUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastNameString = lastName.getText().toString();
                firstNameString = firstName.getText().toString();
                userNameString = userName.getText().toString();
                passwordString = password.getText().toString();

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
//                                        System.out.println("Username input" + userNameString);
                                        System.out.println("Username From Database" + usernameFromDatabase);
                                        if(x.getString("authorizationLevel").equals("1") == true){
                                            authorizedUserId = x.getId();

//                                            System.out.println("The Authorized UserId is: " + authorizedUserId);

                                            Toast toast = Toast.makeText(getApplicationContext(), "Authorized user" + usernameFromDatabase,
                                                    Toast.LENGTH_SHORT);
                                            toast.show();

                                            DocumentReference docRef = FirebaseFirestore.getInstance()
                                                    .collection("users")
                                                    .document(authorizedUserId);
                                            System.out.println("The Authorized UserId is: " + authorizedUserId);
                                            Map<String, Object> myMap = new HashMap<>();
                                            myMap.put(UserN_Key, userNameString);
                                            docRef.update(myMap)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Log.d(TAG, "onSuccess: document was updated");
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Log.e(TAG, "onFailure: ", e);
                                                        }
                                                    });
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        btnUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordString = password.getText().toString();

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
//                                        System.out.println("Username input" + userNameString);
                                        System.out.println("Username From Database" + usernameFromDatabase);
                                        if(x.getString("authorizationLevel").equals("1") == true){
                                            authorizedUserId = x.getId();

//                                            System.out.println("The Authorized UserId is: " + authorizedUserId);

                                            Toast toast = Toast.makeText(getApplicationContext(), "Authorized user" + usernameFromDatabase,
                                                    Toast.LENGTH_SHORT);
                                            toast.show();

                                            DocumentReference docRef = FirebaseFirestore.getInstance()
                                                    .collection("users")
                                                    .document(authorizedUserId);
                                            System.out.println("The Authorized UserId is: " + authorizedUserId);
                                            Map<String, Object> myMap = new HashMap<>();
                                            myMap.put(Password_Key, passwordString);
                                            docRef.update(myMap)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Log.d(TAG, "onSuccess: document was updated");
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Log.e(TAG, "onFailure: ", e);
                                                        }
                                                    });
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        btnUpdateFirstName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNameString = firstName.getText().toString();
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
//                                        System.out.println("Username input" + userNameString);
                                        System.out.println("Username From Database" + usernameFromDatabase);
                                        if(x.getString("authorizationLevel").equals("1") == true){
                                            authorizedUserId = x.getId();

//                                            System.out.println("The Authorized UserId is: " + authorizedUserId);

                                            Toast toast = Toast.makeText(getApplicationContext(), "Authorized user" + usernameFromDatabase,
                                                    Toast.LENGTH_SHORT);
                                            toast.show();

                                            DocumentReference docRef = FirebaseFirestore.getInstance()
                                                    .collection("users")
                                                    .document(authorizedUserId);
                                            System.out.println("The Authorized UserId is: " + authorizedUserId);
                                            Map<String, Object> myMap = new HashMap<>();
                                            myMap.put(FirstN_Key, firstNameString);
                                            docRef.update(myMap)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Log.d(TAG, "onSuccess: document was updated");
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Log.e(TAG, "onFailure: ", e);
                                                        }
                                                    });
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        btnUpdateLastName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastNameString = lastName.getText().toString();
                firstNameString = firstName.getText().toString();
                userNameString = userName.getText().toString();
                passwordString = password.getText().toString();

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
//                                        System.out.println("Username input" + userNameString);
                                        System.out.println("Username From Database" + usernameFromDatabase);
                                        if(x.getString("authorizationLevel").equals("1") == true){
                                            authorizedUserId = x.getId();

//                                            System.out.println("The Authorized UserId is: " + authorizedUserId);

                                            Toast toast = Toast.makeText(getApplicationContext(), "Authorized user" + usernameFromDatabase,
                                                    Toast.LENGTH_SHORT);
                                            toast.show();

                                            DocumentReference docRef = FirebaseFirestore.getInstance()
                                                    .collection("users")
                                                    .document(authorizedUserId);
                                            System.out.println("The Authorized UserId is: " + authorizedUserId);
                                            Map<String, Object> myMap = new HashMap<>();
                                            myMap.put(LastN_Key, lastNameString);
                                            docRef.update(myMap)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Log.d(TAG, "onSuccess: document was updated");
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Log.e(TAG, "onFailure: ", e);
                                                        }
                                                    });
                                        }
                                    }
                                }
                            }
                        });
            }
        });








    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

    }

    @Override
    protected void onStop() {
        super.onStop();
        userSnapshotListener.remove();
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