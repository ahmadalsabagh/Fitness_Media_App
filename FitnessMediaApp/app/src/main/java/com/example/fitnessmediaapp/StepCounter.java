package com.example.fitnessmediaapp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StepCounter extends AppCompatActivity {
    double cals = 0.0;
    CountDownTimer yourCountDownTimer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_count);

        Button startTimer = findViewById(R.id.startTimer);
        Button stopTimer = findViewById(R.id.stopTimer);
        final TextView stepsOutput = findViewById(R.id.stepsOutput);
        final TextView caloriesOutput = findViewById(R.id.caloriesOutput);
        final TextView timeOutput = findViewById(R.id.timeOutput);


        startTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cals = 0.0;
                final long maxCounter = 30000;
                long diff = 1000;

                yourCountDownTimer = new CountDownTimer(maxCounter , diff ) {

                    public void onTick(long millisUntilFinished) {
                        String formattedValue = String.format("%.2f", cals);
                        long diff = maxCounter - millisUntilFinished;
                        stepsOutput.setText("" + diff  / 1000);
                        caloriesOutput.setText("" + formattedValue);
                        timeOutput.setText("" + diff  / 1000);
                        cals = cals + 0.04;
                    }

                    public void onFinish() {

                    }


                }.start();
            }
        });

        stopTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yourCountDownTimer.cancel();
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
