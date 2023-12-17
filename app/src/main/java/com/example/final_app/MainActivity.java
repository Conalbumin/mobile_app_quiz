package com.example.final_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private AppCompatButton homeBtn, profileBtn, favoriteBtn, libraryBtn;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private View include;
    private TextView helloUser;
    private RecyclerView folderRecyclerView;
    private FolderAdapter folderAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        // Update the welcome message
        helloUser = findViewById(R.id.helloUser);
        helloUser.setText("Welcome, " + currentUser.getDisplayName() + " !");

        // Initialize RecyclerView
        folderRecyclerView = findViewById(R.id.folderRecyclerView);
        folderRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch and display folders
        fetchFolders();

        // khai bao (declaration, assuming these are buttons)
        homeBtn = findViewById(R.id.homeBtn);
        favoriteBtn = findViewById(R.id.favoriteBtn);
        libraryBtn = findViewById(R.id.libraryBtn);
        profileBtn = findViewById(R.id.profileBtn);

        profileBtn.setOnClickListener(v -> {
            Intent intent=new Intent(this, Profile.class);
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

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            // If user is not signed in, go to the login activity
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        } else {
            // If user is signed in, check if their email is verified
            if (!currentUser.isEmailVerified()) {
                // If email is not verified, go to the email verification activity
                Intent intent = new Intent(MainActivity.this, check_email_verification.class);
                startActivity(intent);
                finish();
            }
        }
    }

    private void fetchFolders() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference dbFolder = db.collection("Folder");

        dbFolder.get().addOnSuccessListener(queryDocumentSnapshots -> {
            ArrayList<Folder> folderArrayList = new ArrayList<>();
            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                Folder folder = documentSnapshot.toObject(Folder.class);
                folder.setFolderId(documentSnapshot.getId());
                folderArrayList.add(folder);
            }
            updateFolderList(folderArrayList);
        }).addOnFailureListener(e -> {
            Toast.makeText(MainActivity.this, "Error fetching folders: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    private void updateFolderList(ArrayList<Folder> folderArrayList) {
        // Update the RecyclerView with the list of folders
        folderAdapter = new FolderAdapter(folderArrayList, this);
        folderRecyclerView.setAdapter(folderAdapter);
    }
}

