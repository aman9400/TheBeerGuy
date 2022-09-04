package com.example.SplashScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.thebeerguy.DashBoard.DashBoard;
import com.example.thebeerguy.Intro.IntroScreen;
import com.example.thebeerguy.Intro.LandingScreen;
import com.example.thebeerguy.R;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();


//        new Thread().start();

        Thread t = new Thread();


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean IsVisited = prefs.getBoolean("IsVisited", false); // get value of last login status

        if (IsVisited) {
            startActivity(new Intent(this, LandingScreen.class));
           finish();

        } else {
            new Handler().postDelayed(() -> {
                startActivity(new Intent(SplashScreen.this, IntroScreen.class));
                finish();
            }, SPLASH_SCREEN_TIME_OUT);
        }

    }


}