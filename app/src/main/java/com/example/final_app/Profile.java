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
        mAuth.getInstance().signOut();
        // Update login status to false in SharedPreference
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();

        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);

    }
}
