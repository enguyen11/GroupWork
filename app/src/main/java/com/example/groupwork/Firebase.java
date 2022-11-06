package com.example.groupwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

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
            Intent openAccount = new Intent(Firebase.this, Chat.class);
            openAccount.putExtra("userID", username);
            FirebaseDatabase db = FirebaseDatabase.getInstance("https://cs5220-dndapp-default-rtdb.firebaseio.com/");
            DatabaseReference mDatabase = db.getReference("Users");
            User user = new User(username);
            mDatabase.child(user.userName).setValue(user);
            user.friendsList.add("User1");
            StickerMessage message = new StickerMessage("User1", username, "Hi, welcome to StickerShare!");
            user.messageList.add(message);
            mDatabase.child(user.userName).child("Friends").setValue(user.friendsList);
            mDatabase.child(user.userName).child("Conversations").setValue(user.messageList);
           // mDatabase = db.getReference("friends");
           // mDatabase.setValue(username);
            Firebase.this.startActivity(openAccount);
        });

    }
}