package com.example.groupwork.DNDChat;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class ChatSelection extends AppCompatActivity implements ChatSelectionRecyclerViewInterface{

    private static ArrayList<ClickableChat> chatList;
    private ClickableChatAdapter chatListAdapter;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;

    public static String SENDER;
    public static String CAMPAIGN_NAME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_selection);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.SENDER = extras.getString("userID");
            this.CAMPAIGN_NAME = extras.getString("campaignName");
        }

        chatList = new ArrayList<>();
        //this.chatList.add(new ClickableChat("Test"));

        RecyclerView chatSelection = findViewById(R.id.chatSelection);
        chatListAdapter = new ClickableChatAdapter(chatList, chatSelection.getContext(), this);
        chatSelection.setAdapter(chatListAdapter);
        chatSelection.setLayoutManager(new LinearLayoutManager(ChatSelection.this));


        db = FirebaseDatabase.getInstance("https://dndapp-b52b2-default-rtdb.firebaseio.com/");
        StringBuilder path = new StringBuilder("Games/");
        path.append(CAMPAIGN_NAME);
        path.append("/ChatRoom");
        System.out.println(path.toString());
        mDatabase = db.getReference(path.toString());
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot mySnapShot: snapshot.getChildren()){
                    System.out.println("Key: " + mySnapShot.getKey());
                    System.out.println("Value: " + mySnapShot.getValue());
                    ChatSelection.chatList.add(new ClickableChat(String.valueOf(mySnapShot.getKey())));
                    chatListAdapter.notifyDataSetChanged();
                }
                //System.out.println("My List: " + Arrays.toString(chatList.toArray()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });





    }

    @Override
    public void onChatClick(int position) {
        Intent goToChat = new Intent(ChatSelection.this, DnDChat.class);
        String chatName = chatList.get(position).getChatName();
        //System.out.println("chat Name: " + chatName);
        goToChat.putExtra("userID", SENDER);
        goToChat.putExtra("campaignName", CAMPAIGN_NAME);
        goToChat.putExtra("chatName", chatName);
        ChatSelection.this.startActivity(goToChat);

    }
}