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
import com.example.waterdrinkingapp.db.RegisterEntity;

public class Register extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private boolean passCheck = false;
    private String registerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RegisterEntity registerEntity = new RegisterEntity();

        TextView incomplete = findViewById(R.id.incomplete);
        EditText firstName = (EditText) findViewById(R.id.firstNameRegister);
        firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null) {
                    incomplete.setVisibility(View.INVISIBLE);
                    registerEntity.setFirstName(s.toString());
                }
            }
        });

        EditText lastName = (EditText) findViewById(R.id.lastNameRegister);
        lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null) {
                    incomplete.setVisibility(View.INVISIBLE);
                    registerEntity.setLastName(s.toString());
                }
            }
        });

        EditText email = (EditText) findViewById(R.id.emailRegister);
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null) {
                    incomplete.setVisibility(View.INVISIBLE);
                    registerEntity.setEmail(s.toString());
                }
            }
        });

        EditText password = (EditText) findViewById(R.id.passwordRegister);
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null) {
                    incomplete.setVisibility(View.INVISIBLE);
                    registerEntity.setPassword(s.toString());
                }
            }
        });

        EditText passwordConfirmed = (EditText) findViewById(R.id.passwordConfirmRegister);
        passwordConfirmed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null) {
                    incomplete.setVisibility(View.INVISIBLE);
                    if (registerEntity.getPassword().equals(s.toString())) {
                        passCheck = true;
                        passwordConfirmed.setError(null);
                    } else {
                        passwordConfirmed.setError("The password is not the same!");
                    }
                }
            }
        });

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (registerEntity.getFirstName() == null || registerEntity.getLastName() == null
                        || registerEntity.getEmail() == null || registerEntity.getPassword() == null) {
                    incomplete.setVisibility(View.VISIBLE);
                } else {
                    incomplete.setVisibility(View.INVISIBLE);
                    if (passCheck) {
                        new Thread() {
                            @Override
                            public void run() {
                                AppDatabase.getDatabase(Register.this).registerDao().insertEntity(registerEntity);
                            }
                        }.start();

                        Intent intent = new Intent(v.getContext(), InfoActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}