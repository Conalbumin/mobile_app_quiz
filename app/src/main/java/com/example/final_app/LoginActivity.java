package com.example.final_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity
        implements View.OnClickListener {

    private EditText editTextEmail, editTextPassword;
    private Button cirLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        cirLoginButton = findViewById(R.id.cirLoginButton);

        cirLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        handleClickEvent();
    }

    private void handleClickEvent() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(this, "Please enter your email",
                    Toast.LENGTH_SHORT).show();
            editTextEmail.requestFocus();
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Please enter your password",
                    Toast.LENGTH_SHORT).show();
            editTextPassword.requestFocus();
        } else if (email.equals("admin@gmail.com") && password.equals("123456")) {
            // Lưu trữ thông tin đăng nhập của người dùng trong SharedPreferences
            SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("email", email);
            editor.putString("password", password);
            editor.apply();

            // Chuyển đến activity_main
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            finish(); // close
        } else {
            Toast.makeText(this, "Email hoặc mật khẩu không chính xác",
                    Toast.LENGTH_SHORT).show();
        }
    }


}
