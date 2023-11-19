package com.example.final_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {
    private Button logOut, backtohome;
    private SharedPreferences sharedPreferences;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_profile);

        logOut = findViewById(R.id.logOut);
        backtohome = findViewById(R.id.backtohome);
        sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        mAuth = FirebaseAuth.getInstance();

        logOut.setOnClickListener(v -> {
            logout();
        });

        backtohome.setOnClickListener(v -> {
            finish(); // This will destroy the current activity
        });

    }

    private void logout() {
        mAuth.signOut();

        // Update login status to false in SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("your_preference_name", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();

        Toast.makeText(this, "Sign out successful.", Toast.LENGTH_SHORT).show();

        // Navigate to your login or registration activity
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
        finish();

    }
}
