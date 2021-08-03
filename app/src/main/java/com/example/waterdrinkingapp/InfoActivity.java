package com.example.waterdrinkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = InfoActivity.class.getSimpleName();
    int activityVal;
    Double weightVal;
    int water_intake;

    FirebaseDatabase database;
    DatabaseReference reference;

    Info info = new Info();
    String receivedUid;
    EditText weight, activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Information");

        Intent registerIntent = getIntent();
        receivedUid = registerIntent.getStringExtra("userUID");

        TextView incomplete_info = findViewById(R.id.incomplete_info);

        weight = findViewById(R.id.weight);
        activity = findViewById(R.id.activity_minutes);

        weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && s.toString() != null) {
                    incomplete_info.setVisibility(View.INVISIBLE);
                } else {
                    incomplete_info.setVisibility(View.VISIBLE);
                }
            }
        });

        activity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null) {
                    incomplete_info.setVisibility(View.INVISIBLE);
                } else {
                    incomplete_info.setVisibility(View.VISIBLE);
                }
            }
        });


        Button saveBtn = findViewById(R.id.saveButton);
        saveBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String uid = receivedUid;
        Double etWeight = Double.valueOf(weight.getText().toString().trim());
        Integer etActivity = Integer.valueOf(activity.getText().toString().trim());
        Integer waterIntake = water_intake = (int) (((weightVal / 30) + (((double) activityVal / 30) * 0.35)) * 1000);
        Integer currentQuantity = 0;
        Integer percent = 0;

        if (TextUtils.isEmpty(etWeight.toString()) && TextUtils.isEmpty(etActivity.toString())) {
            // if the text fields are empty
            // then show the below message.
            Toast.makeText(InfoActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();
        } else {
            // else call the method to add
            // data to our database.
            addDataToFirebase(uid, etWeight, etActivity, waterIntake, currentQuantity, percent);
        }

    }

    private void addDataToFirebase(String uid, Double etWeight, Integer etActivity, Integer waterIntake, Integer currentQuantity, Integer percent) {
        info.setRegisterId(uid);
        info.setWeight(etWeight);
        info.setActivity(etActivity);
        info.setWaterIntake(waterIntake);
        info.setCurrentQuantity(currentQuantity);
        info.setPercent(percent);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                reference.child("uid").child(uid).setValue(info);

                // after adding this data we are showing toast message.
                Toast.makeText(InfoActivity.this, "Data saved", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(InfoActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}