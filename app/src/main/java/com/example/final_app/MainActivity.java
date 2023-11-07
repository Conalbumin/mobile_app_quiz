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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button buttonLogin, buttonRegister, buttonLogout;
    private RelativeLayout layoutRegister, layoutLogin;
    private FirebaseAuth auth;
    private TextView textView;
    private FirebaseUser user;
    private View include;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check login status using SharedPreference
        SharedPreferences sharedPreferences = getSharedPreferences("isLoggedIn", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (!isLoggedIn) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        } else {
            // Proceed with main activity's functionality
            auth = FirebaseAuth.getInstance();
            buttonLogout = findViewById(R.id.logOut);
            user = auth.getCurrentUser();

            textView.setText(user.getEmail());

            buttonLogout.setOnClickListener(view -> {
                FirebaseAuth.getInstance().signOut();

                // Update login status to false in SharedPreference
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            });
        }
    }
}
