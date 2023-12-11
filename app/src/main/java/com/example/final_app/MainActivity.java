package com.example.final_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button buttonLogin, buttonRegister, buttonLogout;
    private AppCompatButton homeBtn, profileBtn, favoriteBtn, libraryBtn;
    private RelativeLayout layoutRegister, layoutLogin;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private View include;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();

        // khai bao (declaration, assuming these are buttons)
        homeBtn = findViewById(R.id.homeBtn);
        favoriteBtn = findViewById(R.id.favoriteBtn);
        libraryBtn = findViewById(R.id.libraryBtn);
        profileBtn = findViewById(R.id.profileBtn);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(!user.isEmailVerified()){
            Intent intent=new Intent(MainActivity.this, check_email_verification.class);
            startActivity(intent);
            finish();
        }



        profileBtn.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, Profile.class);
            startActivity(intent);
        });

        libraryBtn.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, libraryActivity.class);
            startActivity(intent);

        });

        favoriteBtn.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, Favorite.class);
            startActivity(intent);

        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            Intent intent=new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        }

    }



}

