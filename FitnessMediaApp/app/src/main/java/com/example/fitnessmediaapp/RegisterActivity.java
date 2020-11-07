package com.example.fitnessmediaapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    public static final String FirstN_Key = "firstName";
    public static final String LastN_Key = "lastName";
    public static final String UserN_Key = "username";
    public static final String Password_Key = "password";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static String firstNameString;
    public static String lastNameString;
    public static String userNameString;
    public static String passwordString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final Map<String, Object> user = new HashMap<>();
        final EditText firstName = findViewById(R.id.firstNameTxt);
        final EditText lastName = findViewById(R.id.lastNameTxt);
        final EditText userName = findViewById(R.id.userNameTxt);
        final EditText password = findViewById(R.id.passwordTxt);
        Button btnRegister = findViewById(R.id.registerBtn);

//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                lastNameString = lastName.getText().toString();
//                firstNameString = firstName.getText().toString();
//                userNameString = userName.getText().toString();
//                passwordString = password.getText().toString();
//                user.put(FirstN_Key, firstNameString);
//                user.put(LastN_Key, lastNameString);
//                user.put(UserN_Key, userNameString);
//                user.put(Password_Key, passwordString);
//
//                db.collection("users")
//                        .add(user)
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
//            }
//        });

//         Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Register");  // provide compatibility to all the versions
        }
    }
}