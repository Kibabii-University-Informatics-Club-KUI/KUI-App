package com.kanyideveloper.kuiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user != null){
                   Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                   startActivity(intent);
                   finish();

                }else{
                    Intent intent = new Intent(SplashActivity.this, AuthBoardActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);
    }
}