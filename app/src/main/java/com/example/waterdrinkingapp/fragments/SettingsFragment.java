package com.example.waterdrinkingapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waterdrinkingapp.R;

public class SettingsFragment extends Fragment {
    private final String TAG = SettingsFragment.class.getSimpleName();
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

//        recyclerView = view.findViewById(R.id.recyclerView);

        return view;
    }
}