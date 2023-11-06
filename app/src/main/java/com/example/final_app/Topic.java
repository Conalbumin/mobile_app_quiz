package com.example.final_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Topic extends AppCompatActivity {

    private ListView listViewTopics;
    private Button buttonAddTopic;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate file activity_topic.xml
        View view = LayoutInflater.from(this).inflate(R.layout.activity_topic, null);

        // Gán ListView có ID listViewTopics cho biến listViewTopics
        listViewTopics = view.findViewById(R.id.listViewTopics);

        // Thiết lập sự kiện click cho Button có ID btnAddTopic
        buttonAddTopic = view.findViewById(R.id.btnAddTopic);
        buttonAddTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị dialog để thêm một chủ đề mới
                // ...
            }
        });

        // Thêm ListView vào layout
        setContentView(view);

        // Lấy danh sách các chủ đề
        // ...

        // Tạo adapter cho ListView
        // ...

        // Gán adapter cho ListView
        listViewTopics.setAdapter(adapter);
    }
}


