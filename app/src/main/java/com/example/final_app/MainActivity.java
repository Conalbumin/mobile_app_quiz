package com.example.final_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Kiểm tra xem người dùng đã đăng nhập hay chưa
        if (!isLoggedIn()) {
            // Nếu người dùng chưa đăng nhập, mở activity_login
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }


    }

    private boolean isLoggedIn() {
        // Kiểm tra xem có bất kỳ thông tin đăng nhập nào được lưu trữ trong SharedPreferences không
        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        String email = preferences.getString("email", null);
        String password = preferences.getString("password", null);

        return email != null && password != null;
    }
}
