package com.mosangeet.splashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.mosangeet.HomeScreen;
import com.mosangeet.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // on below line we are configuring our window to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        // on below line we are calling handler to run a task
        // for specific time interval
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // on below line we are
                // creating a new intent
                Intent i = new Intent(SplashScreen.this, HomeScreen.class);

                // on below line we are
                // starting a new activity.
               startActivity(i);

                // on the below line we are finishing
                // our current activity.
                finish();
            }
        }, 7000);

    }




}