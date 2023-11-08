package com.example.final_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Recovery extends AppCompatActivity {

    private EditText emailEditText;
    private Button resetPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);

        emailEditText = findViewById(R.id.editTextEmail);
        resetPasswordButton = findViewById(R.id.resetPasswordButton);

        resetPasswordButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            if (email.isEmpty()) {
                emailEditText.setError("Please enter your email address");
                return;
            }
            sendResetPasswordEmail(email);
        });
    }

    private void sendResetPasswordEmail(String email) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Reset Password");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Please click on the following link to reset your password:\n\n" + getResetPasswordLink(email));
        startActivity(Intent.createChooser(emailIntent, "Send Email"));
    }

    private String getResetPasswordLink(String email) {
         FirebaseAuth auth = FirebaseAuth.getInstance();
         auth.sendPasswordResetEmail(email)
             .addOnCompleteListener(task -> {
                 if (task.isSuccessful()) {
                     Log.d("Recovery", "Password reset email sent");
                 } else {
                     Log.w("Recovery", "Failed to send password reset email", task.getException());
                 }
             });
        return email;
    }
}

