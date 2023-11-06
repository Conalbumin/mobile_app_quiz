package com.example.final_app;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class studySetSpinner extends AppCompatActivity{
    private Spinner spinner;
    private List<String> data;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_studyset);

        spinner=findViewById(R.id.spinner);


        data=new ArrayList<>();
        data.add("All");
        data.add("Created");
        data.add("Studied");

        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,data);
        spinner.setAdapter(adapter);




    }

}
