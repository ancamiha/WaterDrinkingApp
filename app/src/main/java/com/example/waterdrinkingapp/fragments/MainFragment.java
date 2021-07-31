package com.example.waterdrinkingapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waterdrinkingapp.Adapter;
import com.example.waterdrinkingapp.MainActivity;
import com.example.waterdrinkingapp.R;
import com.github.lzyzsd.circleprogress.CircleProgress;

import java.util.ArrayList;


public class MainFragment extends Fragment {
    private final String TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<String> items;
    CircleProgress circleProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        items = new ArrayList<>();
        items.add("Card 1");
        items.add("Card 2");

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), items);
        recyclerView.setAdapter(adapter);


        circleProgress = view.findViewById(R.id.circle_progress);
        circleProgress.setProgress(60);
        return view;
    }
}