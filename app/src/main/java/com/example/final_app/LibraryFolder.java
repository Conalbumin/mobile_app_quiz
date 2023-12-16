package com.example.final_app;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class LibraryFolder extends Fragment {

    public LibraryFolder(){}

    private Button createFolder;
    private RecyclerView folderRV;
    private FolderAdapter folderAdapter;
    private LinearLayout FolderCreateLayout, FolderListLayout;
    private ArrayList<Folder> folderArrayList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.layout_library_folder,container,false);

        createFolder= view.findViewById(R.id.createFolderBtn);

        createFolder.setOnClickListener(v -> {
            openDialog();
        });

        FolderCreateLayout=view.findViewById(R.id.FolderCreateLayout);
        FolderListLayout=view.findViewById(R.id.FolderListLayout);

        folderRV=view.findViewById(R.id.folderRV);
        fetchFolders();

        FirebaseFirestore db=FirebaseFirestore.getInstance();
        CollectionReference dbFolder=db.collection("Folder");

        dbFolder.addSnapshotListener((value, error) -> {
            Toast.makeText(getContext(),"Updated",Toast.LENGTH_SHORT).show();
            fetchFolders();
        });

        return view;
    }

    private void fetchFolders(){
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        CollectionReference dbFolder=db.collection("Folder");

        dbFolder.get().addOnSuccessListener(queryDocumentSnapshots ->  {
            folderArrayList=new ArrayList<>();
            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                Folder folder = documentSnapshot.toObject(Folder.class);
                folder.setFolderId(documentSnapshot.getId());
                folderArrayList.add(folder);
            }
            updateUI();
        });

    }

    private void updateUI(){
        if (folderArrayList != null && folderArrayList.size() > 0) {
            // Folders exist, show the folder list layout
            FolderCreateLayout.setVisibility(View.GONE);
            FolderListLayout.setVisibility(View.VISIBLE);

            folderAdapter = new FolderAdapter(folderArrayList, getContext());

            FirebaseFirestore db= FirebaseFirestore.getInstance();
            CollectionReference dbFolder=db.collection("Folder");

            // Set item click listener for the adapter
            folderAdapter.setOnItemClickListener(folder -> {
                // Handle click on the folder (e.g., open details, navigate to a new fragment)
                Intent intent= new Intent(getContext(),FolderActivity.class);
                intent.putExtra("folderID", folder.getFolderId());
                startActivity(intent);
            });


            folderAdapter.setOnDeleteClickListener(folder -> {
                dbFolder.document(folder.getFolderId());
            });

            folderRV.setLayoutManager(new LinearLayoutManager(getContext()));
            folderRV.setAdapter(folderAdapter);

            Button createNewFolderButton= FolderListLayout.findViewById(R.id.createNewFolderBtn);
            createNewFolderButton.setOnClickListener(v -> openDialog());
        } else {
            // No folders, show the folder creation layout
            FolderCreateLayout.setVisibility(View.VISIBLE);
            FolderListLayout.setVisibility(View.GONE);
        }
    }

    private void openDialog(){
        Dialog dialog=new Dialog(getActivity());
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

        cancelBtn.setOnClickListener(v -> {
            dialog.dismiss();

        });

        OKBtn.setOnClickListener(v -> {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();

            FirebaseFirestore db= FirebaseFirestore.getInstance();
            EditText folderNameEdit=dialog.findViewById(R.id.folderNameEditText);
            EditText folderDesEdit=dialog.findViewById(R.id.folderDesEditText);


            String FolderName = folderNameEdit.getText().toString();
            String FolderDes= folderDesEdit.getText().toString();

            FirebaseUser currentUser = mAuth.getCurrentUser();
            String userName= currentUser.getDisplayName();

            String FolderID = "";

            CollectionReference dbFolder=db.collection("Folder");
            Folder folder= new Folder(FolderName,FolderDes,userName, FolderID);
            dbFolder.add(folder).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    String folderId=documentReference.getId();
                    folder.setFolderId(folderId);
                    dialog.dismiss();
                    fetchFolders();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Fail to add course \n" + e, Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "onFailure: "+e);
                }
            });

        });

        dialog.show();
    }

}
