package com.example.final_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button buttonLogin, buttonRegister, buttonLogout;
    private AppCompatButton homeBtn, profileBtn, favoriteBtn, libraryBtn;
    private RelativeLayout layoutRegister, layoutLogin;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private View include;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // khai bao (declaration, assuming these are buttons)
        homeBtn = findViewById(R.id.homeBtn);
        favoriteBtn = findViewById(R.id.favoriteBtn);
        libraryBtn = findViewById(R.id.libraryBtn);
        profileBtn = findViewById(R.id.profileBtn);

        // Check login status using SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("isLoggedIn", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        // If the user is not logged in, redirect to the Login activity
        if (!isLoggedIn) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        } else {
            // Set up a single click listener for all buttons
            View.OnClickListener buttonClickListener = v -> {
                Intent intent;
                int id = v.getId();
                if (id == R.id.favoriteBtn) {// Start MainActivity
                    intent = new Intent(getApplicationContext(), Topic.class);
                } else if (id == R.id.libraryBtn) {// Start libraryActivity
                    intent = new Intent(getApplicationContext(), libraryActivity.class);
                } else if (id == R.id.profileBtn) {// Start Profile activity
                    intent = new Intent(getApplicationContext(), Profile.class);
                } else {
                    return; // Do nothing for other buttons
                }
                startActivity(intent);
            };

            // Set the click listener for all buttons
            favoriteBtn.setOnClickListener(buttonClickListener);
            libraryBtn.setOnClickListener(buttonClickListener);
            profileBtn.setOnClickListener(buttonClickListener);
        }
    }

}

