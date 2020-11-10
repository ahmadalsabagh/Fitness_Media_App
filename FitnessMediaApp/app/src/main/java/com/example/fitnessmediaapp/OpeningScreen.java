package com.example.fitnessmediaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OpeningScreen extends AppCompatActivity  {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button register = findViewById(R.id.registerBtn);
        Button login = findViewById(R.id.loginBtn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchActivity = new Intent(OpeningScreen.this,RegisterActivity.class);
                startActivity(switchActivity);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchActivity = new Intent(OpeningScreen.this,AccountSettings.class);
                startActivity(switchActivity);
            }
        });
    }
}