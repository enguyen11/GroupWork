package com.example.groupwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Chat extends AppCompatActivity {

    String userID;
    Button friendsButton;
    private ArrayList<StickerMessage> userMessages;
    private ArrayList<StickerMessage> receiverMessages;
    //private String receiver;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;
    private String friendID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_account);
        friendsButton = findViewById(R.id.button_friends);
        friendsButton.setOnClickListener(view -> {
            Intent goToFriends = new Intent(Chat.this, Friends.class);
            goToFriends.putExtra("userID", userID);
            Chat.this.startActivity(goToFriends);
        });

        userMessages = new ArrayList<>();
        receiverMessages = new ArrayList<>();

        db = FirebaseDatabase.getInstance("https://cs5220-dndapp-default-rtdb.firebaseio.com/");
        mDatabase = db.getReference("Users");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userID = extras.getString("userID");
        }
        //mDatabase.child(userID).child("messageList").get();

        Button mButton = (Button) findViewById(R.id.send_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean retry = false;
                EditText mEdit = (EditText) findViewById(R.id.sendToUser);
                friendID = mEdit.getText().toString();
                mDatabase.child(userID).child("messageList").get();
                mDatabase.child(friendID).child("messageList").get();
                mEdit = (EditText) findViewById(R.id.message);
                String message = mEdit.getText().toString();

                /*
                setContentView(R.layout.activity_show_sticker);
                if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .add(R.id.fragment_container_view, StickerSelectionFragment.class, null)
                            .commit();
                }*/

                mDatabase.child(userID).child("messageList").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        userMessages.clear();
                        String[] array = new String[3];
                        int num = 0;
                        Iterable<DataSnapshot> outer = task.getResult().getChildren();
                        for(DataSnapshot inner : outer){
                            for(DataSnapshot part : inner.getChildren()){
                                array[num] = part.getValue().toString();
                                num++;
                            }
                            num = 0;
                            StickerMessage message = new StickerMessage(array[2], array[1], array[0]);
                            userMessages.add(message);
                            System.out.println("added from database");
                        }
                        //System.out.println(userMessages.get(0).content);
                    }
                });
                mDatabase.child(friendID).child("messageList").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        receiverMessages.clear();
                        String[] array = new String[3];
                        int num = 0;
                        Iterable<DataSnapshot> outer = task.getResult().getChildren();
                        for(DataSnapshot inner : outer){
                            for(DataSnapshot part : inner.getChildren()){
                                array[num] = part.getValue().toString();
                                num++;
                            }
                            num = 0;
                            StickerMessage message = new StickerMessage(array[2], array[1], array[0]);
                            receiverMessages.add(message);
                            System.out.println("Adding message: " + message);
                        }
                        //System.out.println(userMessages.get(0).content);
                    }
                });
                System.out.println(userMessages);
                System.out.println(receiverMessages);
                sendMessageToFirebase(message);
            }
        });
    }

    private void sendMessageToFirebase(String message) {
        StickerMessage newMessage = new StickerMessage(userID, friendID, message);
        //getList();
        userMessages.add(newMessage);
        receiverMessages.add(newMessage);
        System.out.println("Updating database");
        mDatabase.child(userID).child("messageList").setValue(userMessages);
        mDatabase.child(friendID).child("messageList").setValue(receiverMessages);
    }



    /*
    private void getList(){
        mDatabase.child(userID).child("messageList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()){
                    //System.out.println(child.getValue().toString());
                    String sender = child.child("sender").getValue().toString();
                    String receiver = child.child("receiver").getValue().toString();
                    String content = child.child("content").getValue().toString();
                   userMessages.add(new StickerMessage(sender, receiver, content));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        mDatabase.child(friendID).child("messageList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()){
                    //receiverMessages.add((StickerMessage)child.getValue());
                    String sender = child.child("sender").getValue().toString();
                    String receiver = child.child("receiver").getValue().toString();
                    String content = child.child("content").getValue().toString();
                    receiverMessages.add(new StickerMessage(sender, receiver, content));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/

}