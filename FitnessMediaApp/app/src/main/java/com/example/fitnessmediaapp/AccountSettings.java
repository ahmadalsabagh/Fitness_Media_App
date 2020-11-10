package com.example.fitnessmediaapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        final Map<String, Object> user = new HashMap<>();
        final EditText firstName = findViewById(R.id.newFirstNameTxt);
        final EditText lastName = findViewById(R.id.newLastNameTxt);
        final EditText userName = findViewById(R.id.newUsernameTxt);
        final EditText password = findViewById(R.id.newPasswordTxt);
        Button btnRegister = findViewById(R.id.updateUserBtn);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastNameString = lastName.getText().toString();
                firstNameString = firstName.getText().toString();
                userNameString = userName.getText().toString();
                passwordString = password.getText().toString();

                DocumentReference docRef = FirebaseFirestore.getInstance()
                        .collection("users")
                        .document("wKmyn526EBQp6IFwusZP");

                Map<String, Object> myMap = new HashMap<>();
                myMap.put(LastN_Key, lastNameString);
                myMap.put(FirstN_Key, firstNameString);
                myMap.put(UserN_Key, userNameString);
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
        });
    }

        //Test onclick for the 'steps' navigation bar button
    public void test(View v){
        Toast toast=Toast. makeText(getApplicationContext(),"Hello Javatpoint",Toast. LENGTH_SHORT);
        toast.show();
    }
}