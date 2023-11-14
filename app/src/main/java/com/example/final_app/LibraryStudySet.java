package com.example.final_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class LibraryStudySet extends Fragment {
    private Spinner spinner;
    private List<String> data;
    private ArrayAdapter<String> adapter;
    public LibraryStudySet(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_library_studyset, container, false);

        spinner=view.findViewById(R.id.spinner);

        data=new ArrayList<>();
        data.add("All");
        data.add("Created");
        data.add("Studied");

        adapter=new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item,data);
        spinner.setAdapter(adapter);


        return view;
    }
}
