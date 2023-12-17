package com.example.final_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class Favorite extends AppCompatActivity {
    private AppCompatButton homeBtn, profileBtn, libraryBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        homeBtn = findViewById(R.id.homeBtn);
        libraryBtn = findViewById(R.id.libraryBtn);
        profileBtn = findViewById(R.id.profileBtn);


        homeBtn.setOnClickListener(v -> {
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        libraryBtn.setOnClickListener(v -> {
            Intent intent=new Intent(this, libraryActivity.class);
            startActivity(intent);
            finish();
        });
        profileBtn.setOnClickListener(v -> {
            Intent intent=new Intent(this, Profile.class);
            startActivity(intent);
            finish();
        });
    }
}
