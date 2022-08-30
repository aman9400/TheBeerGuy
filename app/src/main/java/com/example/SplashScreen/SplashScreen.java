package com.example.SplashScreen;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.thebeerguy.Intro.IntroScreen;
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



//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        String checkSkipPressed = sharedPreferences.getString("intro","no");
        new Handler().postDelayed(() -> {
//            if(checkSkipPressed.equalsIgnoreCase("yes")){
//                startActivity(new Intent(Splash.this, Login.class));
//            }else {
//                startActivity(new Intent(Splash.this, IntroScreen.class));
//
//            }
            startActivity(new Intent(SplashScreen.this, IntroScreen.class));
            finish();
        }, SPLASH_SCREEN_TIME_OUT);
    }


}