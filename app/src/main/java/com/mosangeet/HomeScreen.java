package com.mosangeet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mosangeet.Webviews.Demand;
import com.mosangeet.splashScreen.SplashScreen;

public class HomeScreen extends AppCompatActivity {

 private CardView onlinebtn;
    private CardView localbtn;
   private CardView downloadbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        onlinebtn=findViewById(R.id.onlineplaybtn);
        localbtn=findViewById(R.id.localplaybtn);
        downloadbtn=findViewById(R.id.downloadplay);


        onlinebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeScreen.this, Demand.class);
                startActivity(i);

            }
        });

        localbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeScreen.this,MainActivity.class);
                startActivity(i);

            }
        });

    }
}