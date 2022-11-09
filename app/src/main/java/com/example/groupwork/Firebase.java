package com.example.groupwork;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Firebase extends AppCompatActivity {

    private Button login_button;
    private EditText username_entry;
    private ArrayList<String> username_list;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;


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
            db = FirebaseDatabase.getInstance("https://cs5220-dndapp-default-rtdb.firebaseio.com/");
            mDatabase = db.getReference("Users");
            User user = new User(username);
            checkIfNew(user);
            Firebase.this.startActivity(openAccount);
        });

    }

    /**
     * Checks to see if the username already exists in the database
     * If it does, nothing happens (otherwise accounts would be overwritten on every login)
     * If it doesn't a new user is added to the database
     * @param user The User instance for the person logging in
     */
    private void checkIfNew(User user){
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean isNew = true;
                for(DataSnapshot child : snapshot.getChildren()) {
                    if(child.child("userName").getValue() != null) {
                        if (child.child("userName").getValue().toString().equals(user.userName)) {
                            isNew = false;
                            break;
                        }
                    }
                }
                if(isNew){
                    makeNew(user);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /**
     * Adds some default values to the user object
     * Creates a new user in the database
     * @param user The User instance for the person logging in
     */
    private void makeNew(User user){
        user.friendsList.add("User1");
        StickerMessage message = new StickerMessage("User1", user.userName, "sticker_dnd_logo");
        user.messageList.add(message);
        mDatabase.child(user.userName).setValue(user);
    }
}