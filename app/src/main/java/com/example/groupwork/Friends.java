package com.example.groupwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Friends extends AppCompatActivity {

    private String userID;
    private ArrayList<String> friendsList;
    private Button btnNewFriend;
    private EditText editNewFriend;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        btnNewFriend = findViewById(R.id.button_add_friend);
        editNewFriend = findViewById(R.id.editTextNewFriend);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userID = extras.getString("userID");
        }
        friendsList = new ArrayList<>(); // replace this with below
        /*
        db = FirebaseDatabase.getInstance("https://cs5220-dndapp-default-rtdb.firebaseio.com/");
        mDatabase = db.getReference("conversation");
        mDatabase.child("friends").child(userID).child("friends");
         */



        RecyclerView friendsView = findViewById(R.id.recycler_friends);
        friendsView.setAdapter(new FriendsAdapter(friendsList, friendsView.getContext()));
        friendsView.setLayoutManager(new LinearLayoutManager(Friends.this));

        btnNewFriend.setOnClickListener(view -> {
            friendsList.add(editNewFriend.getText().toString());//replace this with the below
            /*
              //**Check to see if user exists**
              String friendName = editNewFriend.getText().toString();
              //mDatabase.child("friends").child(friendName) -- how do we search and confirm?
              //if exists
              mDatabase.push().child("friends").child(userId).child(friendName);
             */
            friendsView.getAdapter().notifyDataSetChanged();
        });
    }
}