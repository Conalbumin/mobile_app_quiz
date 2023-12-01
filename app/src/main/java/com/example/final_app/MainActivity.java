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


        profileBtn.setOnClickListener(v -> {
            mAuth.getInstance().signOut();
            Intent intent=new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        });

//
        libraryBtn.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, libraryActivity.class);
            startActivity(intent);

        });

        favoriteBtn.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, Favorite.class);
            startActivity(intent);

        });





        // If the user is not logged in, redirect to the Login activity
//        if (!isLoggedIn) {
//            Intent intent = new Intent(this, Login.class);
//            startActivity(intent);
//        } else {
//            // Set up a single click listener for all buttons
//            View.OnClickListener buttonClickListener = v -> {
//                Intent intent;
//                int id = v.getId();
//                if (id == R.id.favoriteBtn) {// Start MainActivity
//                    intent = new Intent(getApplicationContext(), Topic.class);
//                } else if (id == R.id.libraryBtn) {// Start libraryActivity
//                    intent = new Intent(getApplicationContext(), libraryActivity.class);
//                } else if (id == R.id.profileBtn) {// Start Profile activity
//                    intent = new Intent(getApplicationContext(), Profile.class);
//                } else {
//                    return; // Do nothing for other buttons
//                }
//                startActivity(intent);
//                finish();
//            };
//
//            // Set the click listener for all buttons
//            favoriteBtn.setOnClickListener(buttonClickListener);
//            libraryBtn.setOnClickListener(buttonClickListener);
//            profileBtn.setOnClickListener(buttonClickListener);
//        }
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

