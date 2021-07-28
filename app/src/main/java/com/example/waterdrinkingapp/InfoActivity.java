package com.example.waterdrinkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.waterdrinkingapp.db.AppDatabase;
import com.example.waterdrinkingapp.db.InformationEntity;

public class InfoActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        TextView incomplete_info = findViewById(R.id.incomplete_info);

        InformationEntity informationEntity = new InformationEntity();

        EditText weight = findViewById(R.id.weight);
        weight.addTextChangedListener(new TextWatcher() {
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
                    informationEntity.setWeight(Double.valueOf(s.toString()));
                } else {
                    incomplete_info.setVisibility(View.VISIBLE);
                }
            }
        });


        EditText activity = findViewById(R.id.activity_minutes);
        weight.addTextChangedListener(new TextWatcher() {
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
                    informationEntity.setActivity(Integer.parseInt(s.toString()));
                } else {
                    incomplete_info.setVisibility(View.VISIBLE);
                }
            }
        });

        Button saveBtn = findViewById(R.id.saveButton);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Save button clicked");
                if (incomplete_info.getVisibility() == View.INVISIBLE) {
                    new Thread() {
                        @Override
                        public void run() {
                            String registerId = String.valueOf(AppDatabase.getDatabase(InfoActivity.this).registerDao().getRegisterId());
                            informationEntity.setRegisterId(Integer.parseInt(registerId));
                            AppDatabase.getDatabase(InfoActivity.this).informationDao().insertDetails(informationEntity);
                        }
                    }.start();

                    Intent intent = new Intent(v.getContext(), HomePage.class);
                    startActivity(intent);
                }
            }
        });
    }
}