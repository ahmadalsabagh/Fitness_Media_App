package com.example.fitnessmediaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
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

public class LoginActivity extends AppCompatActivity {


    DatabaseHelper mDatabaseHelper;
    private final LinkedList<UserFireBase> mUsersList = new LinkedList<>();

    private ListenerRegistration userSnapshotListener;

    private static final String TAG = "LoginActivity";
    public static String userNameString;
    public static String passwordString;
    public static String firstNameFromDatabase;
    public static String lastNameFromDatabase;
    public static String usernameFromDatabase;
    public static String passwordFromDatabase;
    public static boolean authenticated = false;
    public static String IdFromDatabase;
    public static final String authorizationLevel_KEY = "authorizationLevel";


//    UserFireBase authenticatedUser = new UserFireBase();

    @Override
    protected void onStart() {
        super.onStart();
        mDatabaseHelper = new DatabaseHelper(this);
        final Map<String, Object> user = new HashMap<>();
        final EditText userName = findViewById(R.id.userNameTxtLogin);
        final EditText password = findViewById(R.id.passwordTxtLogin);
        Button btnLogin = findViewById(R.id.authUserBtn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userNameString = userName.getText().toString();
                passwordString = password.getText().toString();

                userSnapshotListener = FirebaseFirestore.getInstance()
                        .collection("users")
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

                                        firstNameFromDatabase = x.getString("firstName");
                                        lastNameFromDatabase = x.getString("lastName");
                                        passwordFromDatabase = x.getString("password");
                                        usernameFromDatabase = x.getString("username");
                                        IdFromDatabase = x.getId();

//                                        System.out.println("First Name: " + firstNameFromDatabase);
//                                        System.out.println("Last Name: " + lastNameFromDatabase);
//                                        System.out.println("Password: " + passwordFromDatabase);
//                                        System.out.println("username: " + usernameFromDatabase);

                                        UserFireBase userData = new UserFireBase(firstNameFromDatabase, lastNameFromDatabase, passwordFromDatabase, usernameFromDatabase);

                                        //SQLITE add user test
                                        boolean insertData = mDatabaseHelper.createUser(firstNameFromDatabase,lastNameFromDatabase,passwordFromDatabase,usernameFromDatabase);


                                        mUsersList.addLast(userData);
                                    }
                                } else{
                                    Log.e(TAG, "onEvent: query snapshot was null");
                                }

                                for (UserFireBase x: mUsersList){
                                    if(x.getUsername().equals(userNameString) && x.getPassword().equals(passwordString)){
                                        authenticated = true;

//                                        System.out.println("Authenticated!");
                                    }
                                }
                                if (authenticated == true){
//                                    System.out.println("It passes the test of authentication");

                                DocumentReference docRef = FirebaseFirestore.getInstance()
                                     .collection("users")
                                     .document(IdFromDatabase);

                        //Update Authorization level of the user
                        Map<String, Object> myMap = new HashMap<>();
                        myMap.put(authorizationLevel_KEY, "1");
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
                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });
            }

        });

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Login");  // provide compatibility to all the versions
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        userSnapshotListener.remove();
    }
}

