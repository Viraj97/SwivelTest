package com.example.swiveltest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText lEmail, lPassword;
    Button loginBtn;
    FirebaseAuth firebaseAuth;
    TextView register;
    SpinKitView spinKitView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lEmail = findViewById(R.id.lEmail);
        lPassword = findViewById(R.id.lPassword);
        loginBtn = findViewById(R.id.loginBtn);
        register = findViewById(R.id.newRegister);
        firebaseAuth = FirebaseAuth.getInstance();
        spinKitView = findViewById(R.id.spin_kit);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = lEmail.getText().toString().trim();
                String password = lPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    lEmail.setError("Please fill Email");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    lPassword.setError("Please fill the password");
                    return;
                }
                if (password.length() < 5) {
                    lPassword.setError("password must greater than 5");
                    return;
                }
                spinKitView.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Login Sucsessfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Login.this, "Error Login", Toast.LENGTH_SHORT).show();
                            spinKitView.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });
    }
}
