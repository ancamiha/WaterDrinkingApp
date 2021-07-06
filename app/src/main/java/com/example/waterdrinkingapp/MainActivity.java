package com.example.waterdrinkingapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    Runnable runnable = new Runnable() {
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
        handler.postDelayed(runnable, 2000);
    }
}