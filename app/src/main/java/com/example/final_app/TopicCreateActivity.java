package com.example.final_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class TopicCreateActivity extends AppCompatActivity {

    private Button backBtn, addBtn, checkBtn;
    private EditText topicTitle;
    private RecyclerView termRV;
    private TermAdapter termAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_create_topic);

        backBtn = findViewById(R.id.backBtn);
        addBtn = findViewById(R.id.addBtn);
        checkBtn = findViewById(R.id.checkBtn);
        termRV = findViewById(R.id.termRV);

        backBtn.setOnClickListener(v -> {
            finish();
        });

        termAdapter=new TermAdapter(new ArrayList<>(),this);
        termRV.setLayoutManager(new LinearLayoutManager(this));
        termRV.setAdapter(termAdapter);

        checkBtn.setOnClickListener(v -> {
            createNewTopic();
        });


        addBtn.setOnClickListener(v -> {
            // Call a method to add two default terms
            addInitialTerms();
        });

        checkBtn.setOnClickListener(v -> {
            createNewTopic();
        });
    }

    private void createNewTopic(){
        topicTitle= findViewById(R.id.topicName);

        ArrayList<Term> terms=termAdapter.getTermArrayList();

        Topic newTopic = new Topic();
        newTopic.setSetTitle(String.valueOf(topicTitle.getText().toString()));
        newTopic.setTerms(terms);

        saveTopic(newTopic);
    }
    private void saveTopic(Topic topic){
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        CollectionReference dbTopic= db.collection("Topic");

        dbTopic.add(topic).addOnSuccessListener(documentReference -> {
            Toast.makeText(this, "Topic saved successfully", Toast.LENGTH_SHORT).show();
        });
    }

    private void  addInitialTerms(){
        View termCardView = getLayoutInflater().inflate(R.layout.custom_term_card, null);

        EditText termEditText = termCardView.findViewById(R.id.termEditText);
        EditText defEditText = termCardView.findViewById(R.id.defEditText);

        String newTermText = termEditText.getText().toString().trim();
        String newDefText = defEditText.getText().toString().trim();

        Term addterm = new Term(newTermText, newDefText);
        termAdapter.addTerm(addterm);


    }
}
