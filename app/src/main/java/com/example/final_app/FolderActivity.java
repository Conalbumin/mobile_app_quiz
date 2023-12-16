package com.example.final_app;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FolderActivity extends AppCompatActivity {

    private Button backBtn, addBtn, editBtn;

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

        backBtn=findViewById(R.id.backBtn);
        addBtn=findViewById(R.id.addBtn);
        editBtn=findViewById(R.id.editBtn);


        backBtn.setOnClickListener(v -> {
            finish();
        });

        addBtn.setOnClickListener(v -> {
            Dialog dialog=new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.custom_folder_dialog);

            Window window=dialog.getWindow();
            if(window==null){
                return;
            }

            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(true);

            Button cancelBtn= dialog.findViewById(R.id.cancelBtn);
            Button OKBtn= dialog.findViewById(R.id.OKBtn);

            TextView title=dialog.findViewById(R.id.dialogTitle);
            title.setText("Add new set");

            cancelBtn.setOnClickListener(View -> {
                dialog.dismiss();
            });
            dialog.show();
        });

        editBtn.setOnClickListener(v -> {
            Dialog dialog=new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.custom_folder_dialog);

            Window window=dialog.getWindow();
            if(window==null){
                return;
            }

            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(true);

            Button cancelBtn= dialog.findViewById(R.id.cancelBtn);
            Button OKBtn= dialog.findViewById(R.id.OKBtn);

            TextView title=dialog.findViewById(R.id.dialogTitle);
            title.setText("Edit folder");

            cancelBtn.setOnClickListener(View -> {
                dialog.dismiss();
            });

            OKBtn.setOnClickListener(View ->{

                EditText newName= dialog.findViewById(R.id.folderNameEditText);
                EditText newDescription=dialog.findViewById(R.id.folderDesEditText);

                Map<String, Object> newData = new HashMap<>();
                newData.put("folderName", newName.getText().toString());
                newData.put("folderDescription", newDescription.getText().toString());

                FirebaseFirestore db=FirebaseFirestore.getInstance();
                String folderId = intent.getStringExtra("folderID");

                DocumentReference dbFolder=db.collection("Folder").document(folderId);
                dbFolder.update(newData).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(FolderActivity.this, "Folder updated successfully", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        Intent returnIntent= new Intent();
                        returnIntent.putExtra("changeVal",1);
                    }
                });


                dbFolder.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot snapshot = task.getResult();
                        if (snapshot.exists()) {
                            // Update local UI elements with the most recent data
                            String updatedName = snapshot.getString("folderName");
                            TextView folderNameTextView = findViewById(R.id.folderName);
                            folderNameTextView.setText(updatedName);
                        }
                    }
                });
            });


            dialog.show();
        });

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
