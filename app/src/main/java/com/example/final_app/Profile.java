package com.example.final_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
    private Button logOut, backtohome;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_profile);

        logOut = findViewById(R.id.logOut);
        backtohome = findViewById(R.id.backtohome);
        sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

        logOut.setOnClickListener(v -> {
            logout();
        });

        backtohome.setOnClickListener(v -> {
            finish(); // This will destroy the current activity
        });

    }

    private void logout() {
        // Xóa trạng thái đăng nhập
        sharedPreferences.edit().clear().apply();

        // Quay trở về màn hình đăng nhập
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
