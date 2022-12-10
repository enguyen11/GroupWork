package com.example.groupwork.Login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.groupwork.DNDChat.DnDChat;
import com.example.groupwork.DNDChat.Message;
import com.example.groupwork.Menus.RpgBuddyMainMenu;
import com.example.groupwork.R;
import com.example.groupwork.RPG_Model.Player;
import com.example.groupwork.StickerActivity.Chat;
import com.example.groupwork.StickerActivity.StickerMessage;
import com.example.groupwork.StickerActivity.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DnDLogin extends AppCompatActivity {

    private Button login_button;
    private EditText username_entry;
    private ArrayList<String> username_list;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dndlogin);

        login_button = findViewById(R.id.button_DnD_login);
        username_entry = findViewById(R.id.editText_DnDUsername);
        username_list = new ArrayList<>();


        /**
         * When clicked, the database should be queried for a username
         * matching what was entered into username_entry
         * the ArrayList is just for temporary use
         *
         */
        login_button.setOnClickListener(view -> {
            String username = username_entry.getText().toString();
            Intent openAccount = new Intent(DnDLogin.this, DnDChat.class);
            openAccount.putExtra("userID", username);
            db = FirebaseDatabase.getInstance("https://dndapp-b52b2-default-rtdb.firebaseio.com/");
            mDatabase = db.getReference("Users");
            Player user = new Player(username);
            checkIfNew(user);
            Toast.makeText(DnDLogin.this,"Login was successful", Toast.LENGTH_SHORT).show();
            DnDLogin.this.startActivity(openAccount);
        });

    }

    /**
     * Checks to see if the username already exists in the database
     * If it does, nothing happens (otherwise accounts would be overwritten on every login)
     * If it doesn't a new user is added to the database
     * @param user The User instance for the person logging in
     */
    private void checkIfNew(Player user){
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean isNew = true;
                for(DataSnapshot child : snapshot.getChildren()) {
                    if(child.child("name").getValue() != null) {
                        if (child.child("name").getValue().toString().equals(user.getName())) {
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
    private void makeNew(Player user){
        user.addFriendToMsgList("User1");
        Message message = new Message("User1", user.getName(), "Start of a conversation");
        user.addMessageToList(message);
        mDatabase.child(user.getName()).setValue(user);
    }

}