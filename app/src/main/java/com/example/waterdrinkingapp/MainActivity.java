package com.example.waterdrinkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    ImageView imgLogo;
    TextView tvTitle;
    RelativeLayout layout1;

    Handler handler = new Handler();
    Runnable startRunnable = new Runnable() {
        @Override
        public void run() {
            tvTitle.setVisibility(View.GONE);
            layout1.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgLogo = findViewById(R.id.imgLogo);
        tvTitle = findViewById(R.id.tvTitle);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.transition1);
        imgLogo.startAnimation(anim);
        tvTitle.startAnimation(anim);

        layout1 = findViewById(R.id.layer1);
        handler.postDelayed(startRunnable, 1500);

        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Login button clicked");
                Intent intent = new Intent(v.getContext(), Login.class);
                startActivity(intent);
            }
        });

        Button registerBtn = findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Register button clicked");

                Intent intent = new Intent(v.getContext(), Register.class);
                startActivity(intent);
            }
        });
    }
}