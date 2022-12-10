package com.example.groupwork.DNDChat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.groupwork.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatSelection extends AppCompatActivity implements ChatSelectionRecyclerViewInterface{

    private static ArrayList<ClickableChat> chatList = new ArrayList<>();;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;

    public static String SENDER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_selection);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.SENDER = extras.getString("userID");
        }

        this.chatList.add(new ClickableChat("Test"));
        db = FirebaseDatabase.getInstance("https://dndapp-b52b2-default-rtdb.firebaseio.com/");
        StringBuilder path = new StringBuilder("Users");
        path.append("/");
        path.append(SENDER);
        path.append("/CampaignList/Campaigns");
        //System.out.println(path.toString());
        mDatabase = db.getReference(path.toString());
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot mySnapShot: snapshot.getChildren()){
                    //System.out.println("Value: " + mySnapShot.getValue());
                    ChatSelection.chatList.add(new ClickableChat(String.valueOf(mySnapShot.getValue())));

                }
                System.out.println("My List: " + Arrays.toString(chatList.toArray()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });
        System.out.println("My List: " + Arrays.toString(chatList.toArray()));


        RecyclerView chatSelection = findViewById(R.id.chatSelection);
        chatSelection.setAdapter(new ClickableChatAdapter(chatList,
                chatSelection.getContext(), this));
        chatSelection.setLayoutManager(new LinearLayoutManager(ChatSelection.this));
    }

    @Override
    public void onChatClick(int position) {
        Intent goToChat = new Intent(ChatSelection.this, DnDChat.class);
        String chatName = chatList.get(position).getChatName();
        goToChat.putExtra("userID", SENDER);
        goToChat.putExtra("chatName", chatName);
        ChatSelection.this.startActivity(goToChat);

    }
}