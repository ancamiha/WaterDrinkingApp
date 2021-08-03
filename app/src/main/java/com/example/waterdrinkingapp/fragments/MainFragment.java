package com.example.waterdrinkingapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.waterdrinkingapp.Info;
import com.example.waterdrinkingapp.R;
import com.example.waterdrinkingapp.Values;
import com.example.waterdrinkingapp.retrofit.InspirationAPI;
import com.example.waterdrinkingapp.retrofit.Post;
import com.github.lzyzsd.circleprogress.CircleProgress;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainFragment extends Fragment implements View.OnClickListener {
    private final String TAG = MainFragment.class.getSimpleName();
    CircleProgress circleProgress;
    FirebaseDatabase database;
    DatabaseReference reference;

    Button button250, button500, button750;
    TextView reachTarget0, reachTarget1, target0, target1;
    TextView quote, author;

    Info infoProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Information");

        button250 = view.findViewById(R.id.button250);
        button500 = view.findViewById(R.id.button500);
        button750 = view.findViewById(R.id.button750);
        circleProgress = view.findViewById(R.id.circle_progress);
        reachTarget0 = view.findViewById(R.id.reachTarget0);
        reachTarget1 = view.findViewById(R.id.reachTarget1);
        target0 = view.findViewById(R.id.target0);
        target1 = view.findViewById(R.id.target1);
        quote = view.findViewById(R.id.quote);
        author = view.findViewById(R.id.author);

        button250.setOnClickListener(this::onClick);
        button500.setOnClickListener(this::onClick);
        button750.setOnClickListener(this::onClick);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://inspiration.goprogram.ai/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InspirationAPI api = retrofit.create(InspirationAPI.class);
        Call<Post> call = api.getPost();
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    Log.e(TAG, "Code: " + response.code());
                    return;
                }
                quote.setText(response.body().getQuote());
                author.setText("- " + response.body().getAuthor());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

        reference.child("uid").child(Values.getReceivedUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                infoProfile = snapshot.getValue(Info.class);

                if (infoProfile != null) {
                    String uid = infoProfile.registerId;
                    Double weight = infoProfile.weight;
                    Integer activity = infoProfile.activity;
                    Integer waterIntake = infoProfile.waterIntake;
                    Integer currentQuantity = infoProfile.currentQuantity;
                    Integer percent = infoProfile.percent;

                    reachTarget1.setText(currentQuantity + "ml");
                    target1.setText(waterIntake + "ml");
                    circleProgress.setProgress(percent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }


    @Override
    public void onClick(View v) {
        int qty;
        int percent;
        switch (v.getId()) {
            case R.id.button250:
                qty = infoProfile.currentQuantity + 250;
                percent = qty * 100 / infoProfile.getWaterIntake();
                reference.child("uid").child(Values.getReceivedUid()).child("currentQuantity").setValue(qty);
                reference.child("uid").child(Values.getReceivedUid()).child("percent").setValue(percent);
                break;
            case R.id.button500:
                qty = infoProfile.currentQuantity + 500;
                percent = qty * 100 / infoProfile.getWaterIntake();
                reference.child("uid").child(Values.getReceivedUid()).child("currentQuantity").setValue(qty);
                reference.child("uid").child(Values.getReceivedUid()).child("percent").setValue(percent);
                break;
            case R.id.button750:
                qty = infoProfile.currentQuantity + 750;
                percent = qty * 100 / infoProfile.getWaterIntake();
                reference.child("uid").child(Values.getReceivedUid()).child("currentQuantity").setValue(qty);
                reference.child("uid").child(Values.getReceivedUid()).child("percent").setValue(percent);
                break;
        }
    }
}