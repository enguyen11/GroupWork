package com.example.groupwork.Firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.groupwork.Firebase.StickerStuff.StickerMessage;
import com.example.groupwork.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Messages extends AppCompatActivity {

    private String userID;
    private ArrayList<StickerMessage> messageList;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;
    private RecyclerView messageView;
    private ArrayList<String> displayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        messageView = findViewById(R.id.messageView);
        displayList = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userID = extras.getString("userID");
        }
        messageList = new ArrayList<>();
        db = FirebaseDatabase.getInstance("https://cs5220-dndapp-default-rtdb.firebaseio.com/");
        mDatabase = db.getReference("Users");
        messageView.setAdapter(new MessageAdapter(messageList, messageView.getContext()));
        messageView.setLayoutManager(new LinearLayoutManager(Messages.this));

        mDatabase.child(userID).child("messageList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()){
                    String sender = child.child("sender").getValue().toString();
                    String receiver = child.child("receiver").getValue().toString();
                    String content = child.child("content").getValue().toString();
                    StickerMessage message = new StickerMessage(sender, receiver, content);
                    messageList.add(message);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        getList();
    }

    private void getList(){
        mDatabase.child(userID).child("messageList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()){
                    displayList.add("Sent by: " + child.child("sender").getValue().toString() + "\nReceived by: " +
                            child.child("receiver").getValue().toString() + "\n" + child.child("content").getValue().toString() + "\n___\n");
                    messageView.getAdapter().notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}