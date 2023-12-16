package com.example.final_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class check_email_verification extends AppCompatActivity {

    private Button checkVer, resendVer;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_mail_verification);

        checkVer=findViewById(R.id.checkVer);
        resendVer=findViewById(R.id.resendVer);
        mAuth=FirebaseAuth.getInstance();

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if(user.isEmailVerified()){
            Toast.makeText(this, user + " is verify", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, user + " is not verify", Toast.LENGTH_SHORT).show();
        }

        checkVer.setOnClickListener(v -> {
            FirebaseUser checkUser= FirebaseAuth.getInstance().getCurrentUser();
            checkUser.reload();
            if(checkUser.isEmailVerified()){
                Toast.makeText(this, "is verify", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(this, checkUser.getEmail()   +" is not verify", Toast.LENGTH_SHORT).show();
            }
        });
        mAuth.getCurrentUser();
        resendVer.setOnClickListener(v -> {
            sendVerificationEmail(mAuth);
        });
    }

    private void sendVerificationEmail(FirebaseAuth mAuth){
        if(mAuth.getCurrentUser()!=null){
            mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"A new verification link has been sent",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Something is wrong!!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}