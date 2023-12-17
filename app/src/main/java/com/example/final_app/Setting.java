package com.example.final_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Setting extends AppCompatActivity {
    private AppCompatButton homeBtn, profileBtn, favoriteBtn, libraryBtn;
    private LinearLayout changeUsername, changeEmail;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        auth = FirebaseAuth.getInstance();

        homeBtn = findViewById(R.id.homeBtn);
        profileBtn = findViewById(R.id.profileBtn);
        favoriteBtn = findViewById(R.id.favoriteBtn);
        libraryBtn = findViewById(R.id.libraryBtn);
        changeUsername = findViewById(R.id.changeUsername);
        changeEmail = findViewById(R.id.changeEmail);

        homeBtn.setOnClickListener(v -> {
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        libraryBtn.setOnClickListener(v -> {
            Intent intent=new Intent(this, libraryActivity.class);
            startActivity(intent);
            finish();
        });
        favoriteBtn.setOnClickListener(v -> {
            Intent intent=new Intent(this, Favorite.class);
            startActivity(intent);
            finish();
        });
        profileBtn.setOnClickListener(v -> {
            Intent intent=new Intent(this, Profile.class);
            startActivity(intent);
            finish();
        });

        changeUsername.setOnClickListener(v -> showChangeUsernameDialog());
        changeEmail.setOnClickListener(v -> showChangeEmailDialog());

    }
    private void showChangeUsernameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to change your username?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // User confirmed, show the dialog to input new username
                showInputUsernameDialog();
            }
        });
        builder.setNegativeButton("No", null); // Do nothing if the user clicks "No"
        builder.show();
    }

    private void showChangeEmailDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to change your email?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // User confirmed, show the dialog to input new email
                showInputEmailDialog();
            }
        });
        builder.setNegativeButton("No", null); // Do nothing if the user clicks "No"
        builder.show();
    }

    private void showInputUsernameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Inflate a layout for the dialog
        View view = getLayoutInflater().inflate(R.layout.layout_input_username, null);
        EditText editTextUsername = view.findViewById(R.id.userName);
        builder.setView(view);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Get the new username from the EditText
                String newUsername = editTextUsername.getText().toString();

                // Call a method to change the username
                changeUsername(newUsername);
            }
        });
        builder.setNegativeButton("Cancel", null); // Do nothing if the user clicks "Cancel"
        builder.show();
    }

    private void showInputEmailDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Inflate a layout for the dialog
        View view = getLayoutInflater().inflate(R.layout.layout_input_email, null);
        EditText editTextEmail = view.findViewById(R.id.email);
        builder.setView(view);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Get the new email from the EditText
                String newEmail = editTextEmail.getText().toString();

                // Call a method to change the email
                changeEmail(newEmail);
            }
        });
        builder.setNegativeButton("Cancel", null); // Do nothing if the user clicks "Cancel"
        builder.show();
    }

    private void changeUsername(String newUsername) {
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(newUsername)
                    .build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(Setting.this, "Username changed successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Setting.this, "Failed to change username", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void changeEmail(String newEmail) {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            user.updateEmail(newEmail)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(Setting.this, "Email changed successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Setting.this, "Failed to change email", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
