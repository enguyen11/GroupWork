package com.example.groupwork.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.groupwork.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Account_Creation extends AppCompatActivity {

    private FirebaseAuth verifyAccount;
    private Button create;
    private EditText email;
    private EditText password;
    private EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);
        verifyAccount = FirebaseAuth.getInstance();
        email = findViewById(R.id.newEmail);
        password = findViewById(R.id.newPassword);
        username = findViewById(R.id.newDisplayname);
        create = findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyAccount.createUserWithEmailAndPassword(email.getText().toString(),
                                password.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(Account_Creation.this,
                                            "Account was successfully made!" +
                                                    "\nPlease Login to continue", Toast.LENGTH_SHORT).show();
                                }
                                else if (task.isCanceled()){
                                    Toast.makeText(Account_Creation.this,
                                            "Process was canceled!", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Log.d("Account Creation: ", task.getException().getMessage());
                                    Toast.makeText(Account_Creation.this,
                                            "Account creation failed!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                        .setDisplayName(username.getText().toString())
                        .build();
                verifyAccount.getCurrentUser().updateProfile(profileUpdate)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(Task<Void> task) {
                                if (task.isSuccessful()) { //success on updating user profile
                                    Toast.makeText(Account_Creation.this,
                                            "Welcome " + username.getText().toString(), Toast.LENGTH_SHORT).show();
                                } else { //failed on updating user profile
                                    Toast.makeText(Account_Creation.this,
                                            "Username failed!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                Intent goToSearch = new Intent(Account_Creation.this, Login.class);
                Account_Creation.this.startActivity(goToSearch);
            }
        });
    }
}