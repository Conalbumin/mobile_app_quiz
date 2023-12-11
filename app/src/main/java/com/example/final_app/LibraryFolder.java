package com.example.final_app;

import android.app.Dialog;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class LibraryFolder extends Fragment {

    public LibraryFolder(){}

    private Button createFolder;
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

        return view;
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
            FirebaseFirestore db= FirebaseFirestore.getInstance();
            EditText folderNameEdit=dialog.findViewById(R.id.folderNameEditText);
            EditText folderDesEdit=dialog.findViewById(R.id.folderDesEditText);


            String FolderName = folderNameEdit.getText().toString();
            String FolderDes= folderDesEdit.getText().toString();

            CollectionReference dbFolder=db.collection("Folder");
            Folder folder= new Folder(FolderName,FolderDes);

            dbFolder.add(folder).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(getContext() ,"New folder is created", Toast.LENGTH_SHORT).show();
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
