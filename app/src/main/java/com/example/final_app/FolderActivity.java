package com.example.final_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class FolderActivity extends AppCompatActivity {

    private TextView numOfSet, folderName, topicRV;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

        Intent intent=getIntent();
        if (intent.hasExtra("folderID")) {
            String folderId = intent.getStringExtra("folderID");

            // Retrieve the folder document from Firestore
            getFolderFromFirestore(folderId);
        } else {
            Toast.makeText(this, "No folder ID found in the intent", Toast.LENGTH_SHORT).show();
        }


    }

    private void getFolderFromFirestore(String folderId){
        // Get the reference to the folder document
        FirebaseFirestore db= FirebaseFirestore.getInstance();
        DocumentReference dbFolder=db.collection("Folder").document(folderId);
        // Retrieve the folder data
        dbFolder.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot= task.getResult();
                String name=documentSnapshot.getString("folderName");
                TextView folderName=findViewById(R.id.folderName);
                folderName.setText(name+"");
            }
        });
    }
}
