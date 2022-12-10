package com.example.groupwork.DNDChat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.groupwork.R;

import java.util.ArrayList;
import java.util.List;

public class ChatSelection extends AppCompatActivity implements ChatSelectionRecyclerViewInterface{

    private ArrayList<ClickableChat> chatList;
    public static String SENDER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_selection);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.SENDER = extras.getString("userID");
        }

        chatList = new ArrayList<>();
        chatList.add(new ClickableChat("Test"));

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