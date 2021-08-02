package com.example.waterdrinkingapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waterdrinkingapp.Adapter;
import com.example.waterdrinkingapp.R;

import java.util.ArrayList;


public class HistoryFragment extends Fragment {
    private final String TAG = HistoryFragment.class.getSimpleName();

    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<String> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            items.add("Card " + i);
        }

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), items);

        recyclerView.setAdapter(adapter);
        return view;
    }
}