package com.example.final_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {
    private Button logOut, backBtn;
    private SharedPreferences sharedPreferences;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_profile);

//        logOut = findViewById(R.id.logOut);
//        backBtn = findViewById(R.id.backBtn);
//        sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
//        mAuth = FirebaseAuth.getInstance();
//
//        logOut.setOnClickListener(v -> {
//            logout();
//        });
//
//        backBtn.setOnClickListener(v -> {
//            finish(); // This will destroy the current activity
//        });

    }

    private void logout() {
        mAuth.getInstance().signOut();
        // Update login status to false in SharedPreference
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();
        Intent intent = new Intent(Profile.this, Login.class);
        startActivity(intent);
        finish();
    }
}
