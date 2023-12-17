package com.example.final_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class LibraryStudySet extends Fragment {
    private Spinner spinner;
    private List<String> data;
    private ArrayAdapter<String> adapter;
    private LinearLayout TopicCreateLayout, TopicListLayout;
    private ArrayList<Topic> topicArrayList;
    private RecyclerView topicRV;
    private TopicAdapter topicAdapter;

    public LibraryStudySet(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_library_studyset, container, false);

        TopicCreateLayout=view.findViewById(R.id.TopicCreateLayout);
        TopicListLayout=view.findViewById(R.id.TopicListLayout);

        topicRV=view.findViewById(R.id.topicRV);
        fetchTopic();

        FirebaseFirestore db=FirebaseFirestore.getInstance();
        CollectionReference dbTopic=db.collection("Topic");

        dbTopic.addSnapshotListener((value, error) -> {
            Toast.makeText(getContext(),"Updated",Toast.LENGTH_SHORT).show();
            fetchTopic();
        });

        return view;
    }

    private void fetchTopic(){
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        CollectionReference dbFolder=db.collection("Topic");

        dbFolder.get().addOnSuccessListener(queryDocumentSnapshots ->  {
            topicArrayList=new ArrayList<>();
            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                Topic topic = documentSnapshot.toObject(Topic.class);
                topic.setSetId(documentSnapshot.getId());
                topicArrayList.add(topic);
            }
            updateUI();
        });
    }

    private void updateUI(){
        if (topicArrayList != null && topicArrayList.size() > 0) {
            // Folders exist, show the folder list layout
            TopicCreateLayout.setVisibility(View.GONE);
            TopicListLayout.setVisibility(View.VISIBLE);

            topicAdapter = new TopicAdapter(topicArrayList, getContext());
//
            FirebaseFirestore db= FirebaseFirestore.getInstance();
            CollectionReference dbTopic=db.collection("Topic");

//             Set item click listener for the adapter
            topicAdapter.setOnItemClickListener(topic -> {
                // Handle click on the folder (e.g., open details, navigate to a new fragment)
//                Intent intent= new Intent(getContext(),TopicActivity.class);
//                intent.putExtra("setID", topic.getSetId());
//                startActivity(intent);
                Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
            });


            topicAdapter.setOnDeleteClickListener(topic -> {
                dbTopic.document(topic.getSetId());
            });

            topicRV.setLayoutManager(new LinearLayoutManager(getContext()));
            topicRV.setAdapter(topicAdapter);

            Button createNewTopicButton= TopicListLayout.findViewById(R.id.createNewTopicBtn);
            createNewTopicButton.setOnClickListener(v -> {
                Intent intent=new Intent(getContext(),TopicCreateActivity.class);
                startActivity(intent);
            });
        } else {
            // No folders, show the folder creation layout
            TopicCreateLayout.setVisibility(View.VISIBLE);
            TopicListLayout.setVisibility(View.GONE);
        }
    }
}
