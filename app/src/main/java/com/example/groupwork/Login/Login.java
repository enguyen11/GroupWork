package com.example.groupwork.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.groupwork.Menus.RpgBuddyMainMenu;
import com.example.groupwork.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private FirebaseAuth verifyAccount;
    private EditText email;
    private EditText password;
    private Button loginBtn;
    private Button nuUserBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login);
        nuUserBtn = findViewById(R.id.newUser);
        verifyAccount = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyAccount.signInWithEmailAndPassword(email.getText().toString(),
                                password.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(Login.this,
                                            "Login was successful", Toast.LENGTH_SHORT).show();
                                    Intent goToSearch = new Intent(Login.this, RpgBuddyMainMenu.class);
                                    goToSearch.putExtra("user", email.getText().toString());
                                    Login.this.startActivity(goToSearch);
                                }
                                else if (task.isCanceled()){
                                    Toast.makeText(Login.this,
                                            "Process was canceled!", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(Login.this,
                                            "Login failed! Incorrect email or password", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });

        nuUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent goToSearch = new Intent(Login.this, Account_Creation.class);
                Login.this.startActivity(goToSearch);
            }
        });

    }


}