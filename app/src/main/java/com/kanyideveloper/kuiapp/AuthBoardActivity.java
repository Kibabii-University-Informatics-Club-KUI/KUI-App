package com.kanyideveloper.kuiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kanyideveloper.kuiapp.databinding.ActivityAuthBoardBinding;

public class AuthBoardActivity extends AppCompatActivity {

    ActivityAuthBoardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.buttonGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthBoardActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        binding.buttonBoardSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthBoardActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}