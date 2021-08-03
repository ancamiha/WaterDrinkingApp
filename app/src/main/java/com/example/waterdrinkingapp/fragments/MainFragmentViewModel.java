package com.example.waterdrinkingapp.fragments;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainFragmentViewModel extends ViewModel {
    private final String TAG = MainFragmentViewModel.class.getSimpleName();
    private MutableLiveData<Integer> myPercent;
    private MutableLiveData<Integer> toReachTarget;

    MutableLiveData<Integer> getMyPercent() {
        if (myPercent == null) {
            myPercent = new MutableLiveData<>();
        }
        return myPercent;
    }

    MutableLiveData<Integer> getToReachTarget() {
        if (toReachTarget == null) {
            toReachTarget = new MutableLiveData<>();
        }
        return toReachTarget;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroyed");
    }
}
