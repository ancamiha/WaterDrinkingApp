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
import com.example.waterdrinkingapp.db.HistoryDatabase;
import com.example.waterdrinkingapp.db.HistoryEntity;

import java.util.List;


public class HistoryFragment extends Fragment {
    private final String TAG = HistoryFragment.class.getSimpleName();

    RecyclerView recyclerView;
    Adapter adapter;
    public List<HistoryEntity> entities;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);


        entities = HistoryDatabase
                .getDatabase(getContext()).historyDao().getEntities();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), entities);

        recyclerView.setAdapter(adapter);
        return view;
    }
}