package com.example.fitnessmediaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private final LinkedList<UserFireBase> mUsersList = new LinkedList<>();

    private static final String TAG = "LoginActivity";
    public static String userNameString;
    public static String passwordString;
    public static String firstNameFromDatabase;
    public static String lastNameFromDatabase;
    public static String usernameFromDatabase;
    public static String passwordFromDatabase;
    public static boolean authenticated = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Map<String, Object> user = new HashMap<>();
        final EditText userName = findViewById(R.id.userNameTxtLogin);
        final EditText password = findViewById(R.id.passwordTxtLogin);
        Button btnLogin = findViewById(R.id.updateUserBtn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNameString = userName.getText().toString();
                passwordString = password.getText().toString();

                FirebaseFirestore.getInstance()
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

                                        UserFireBase userData = new UserFireBase(firstNameFromDatabase, lastNameFromDatabase, passwordFromDatabase, usernameFromDatabase);

                                        mUsersList.addLast(userData);
                                    }
                                } else{
                                    Log.e(TAG, "onEvent: query snapshot was null");
                                }

                                for (UserFireBase x: mUsersList){
                                    if(x.getUsername().equals(userNameString) && x.getPassword().equals(passwordString)){
                                        authenticated = true;
                                    }
                                }
                                if (authenticated == true){
                                    Intent intent = new Intent(getApplicationContext(), OpeningScreen.class);
                                    startActivity(intent);
                                }
                            }
                        });
            }

        });

        //Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Login");  // provide compatibility to all the versions
        }
    }
}
