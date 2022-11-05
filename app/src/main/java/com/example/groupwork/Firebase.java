package com.example.groupwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Firebase extends AppCompatActivity {

    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);


        // ok first we get the username from the log in screen. There is no authentication
        // so anyone can log in as any user
        Button mButton = (Button) findViewById(R.id.button_login);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText mEdit = (EditText) findViewById(R.id.editText1);
                userID = mEdit.getText().toString();

                Intent goToSearch = new Intent(Firebase.this, Chat.class);
                goToSearch.putExtra("userID", userID);
                Firebase.this.startActivity(goToSearch);
            }
        });
    }

}