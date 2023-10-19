package com.example.final_app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.folder_item);

        ListView listView = findViewById(R.id.listViewTopicsInFolder);

        // Data for the list (you can replace this with your actual data)
        String[] data = {"Mục1", "Mục2", "Mục3"};

        // Create a custom ArrayAdapter with the list_item layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.btnEditFolder, data);

        listView.setAdapter(adapter);
    }
}
