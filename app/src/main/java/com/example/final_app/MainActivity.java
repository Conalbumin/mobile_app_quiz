package com.example.final_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

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
        FirebaseUser currentUser = mAuth.getCurrentUser();

        currentUser.reload().addOnCompleteListener(task -> {
            Toast.makeText(this, "Welcome, " + currentUser.getDisplayName() + " !", Toast.LENGTH_SHORT).show();
        });

        // khai bao (declaration, assuming these are buttons)
        homeBtn = findViewById(R.id.homeBtn);
        favoriteBtn = findViewById(R.id.favoriteBtn);
        libraryBtn = findViewById(R.id.libraryBtn);
        profileBtn = findViewById(R.id.profileBtn);

        profileBtn.setOnClickListener(v -> {
            Intent intent=new Intent(this, Profile.class);
            startActivity(intent);
            finish();
        });

        libraryBtn.setOnClickListener(v -> {
            Intent intent=new Intent(this, libraryActivity.class);
            startActivity(intent);
            finish();
        });

        favoriteBtn.setOnClickListener(v -> {
            Intent intent=new Intent(this, Favorite.class);
            startActivity(intent);
            finish();
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

