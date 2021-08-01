package com.example.waterdrinkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = MainActivity.class.getSimpleName();
    private boolean passCheck = false;

    private LinearLayout linearLayout;
    private EditText etFirstName, etLastName, etEmail, etPassword, etPasswordConfirmed;
    private ProgressBar progressBar;
    private Button registerButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        linearLayout = findViewById(R.id.linearLay);
        linearLayout.setOnClickListener(this);

        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

        etFirstName = findViewById(R.id.firstNameRegister);
        etLastName = findViewById(R.id.lastNameRegister);
        etEmail = findViewById(R.id.emailRegister);
        etPassword = findViewById(R.id.passwordRegister);
        etPasswordConfirmed = findViewById(R.id.passwordConfirmRegister);

        progressBar = findViewById(R.id.progress);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLay:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.registerButton:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String passwordConfirmed = etPasswordConfirmed.getText().toString().trim();

        if (firstName.isEmpty()) {
            etFirstName.setError("First name is required!");
            etFirstName.requestFocus();
            return;
        }
        if (lastName.isEmpty()) {
            etLastName.setError("Last name is required!");
            etLastName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            etEmail.setError("Email is required!");
            etEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please provide valid email!");
            etEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            etPassword.setError("Password is required!");
            etPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            etPassword.setError("Min password length is 6 characters!");
            etPassword.requestFocus();
            return;
        }
        if (passwordConfirmed.isEmpty()) {
            etPasswordConfirmed.setError("Password is required!");
            etPasswordConfirmed.requestFocus();
            return;
        }
        if (!passwordConfirmed.equals(password)) {
            etPasswordConfirmed.setError("The password is not the same!");
            etPasswordConfirmed.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(firstName, lastName, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Register.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();

                                        Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                                        intent.putExtra("userUID", FirebaseAuth.getInstance().getCurrentUser().getUid());
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(Register.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                    }
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                        } else {
                            Toast.makeText(Register.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}