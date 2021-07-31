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

public class Login extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    String userEmail, userPass;
    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email = (EditText) findViewById(R.id.emailLogin);
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                userEmail = s.toString();
            }
        });

        EditText password = (EditText) findViewById(R.id.passwordLogin);
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                userPass = s.toString();
            }
        });


        TextView invalid = findViewById(R.id.invalid);
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        check = AppDatabase.getDatabase(Login.this).registerDao().checkIfExists(userEmail, userPass);
                    }
                }.start();
//                if (check == 1) {
                Log.d(TAG, "Login button clicked");
                Intent intent = new Intent(v.getContext(), HomePage.class);
                startActivity(intent);
                invalid.setVisibility(View.INVISIBLE);
//                }
//                } else {
//                    invalid.setVisibility(View.VISIBLE);
//                    password.getText().clear();
//                }

            }
        });
    }
}