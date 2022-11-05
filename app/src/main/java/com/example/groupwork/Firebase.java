package com.example.groupwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Firebase extends AppCompatActivity {

    private Button login_button;
    private EditText username_entry;
    private ArrayList<String> username_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        login_button = findViewById(R.id.button_enter_login);
        username_entry = findViewById(R.id.editText_Username);
        username_list = new ArrayList<>();


        /**
         * When clicked, the database should be queried for a username
         * matching what was entered into username_entry
         * the ArrayList is just for temporary use
         *
         */
        login_button.setOnClickListener(view -> {
            String username = username_entry.getText().toString();
            Intent openAccount = new Intent(Firebase.this, StickerAccount.class);
            openAccount.putExtra("username", username);
            Firebase.this.startActivity(openAccount);
        });

    }
}