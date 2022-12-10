package com.example.groupwork.DNDChat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.groupwork.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class DnDChat extends AppCompatActivity {

    private TextInputLayout messageArea;
    private TextInputLayout recipientArea;
    private FloatingActionButton send;
    public static ArrayList<Message> messageList;
    public static String SENDER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dn_dchat);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.SENDER = extras.getString("userID");
        }
        messageList = new ArrayList<>();
        this.messageArea = findViewById(R.id.messageBar);
        this.send = findViewById(R.id.send);
        this.recipientArea = findViewById(R.id.receiverBar);
        RecyclerView chatHistory = findViewById(R.id.chatHistory);
        chatHistory.setAdapter(new MessageAdapter(messageList, chatHistory.getContext()));
        chatHistory.setLayoutManager(new LinearLayoutManager(DnDChat.this));

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipient = recipientArea.getEditText().getText().toString();
                String message_content = messageArea.getEditText().getText().toString();
                Message message = new Message(DnDChat.SENDER, recipient, message_content);
                messageList.add(message);
                recipientArea.getEditText().getText().clear();
                messageArea.getEditText().getText().clear();
                chatHistory.getAdapter().notifyDataSetChanged();

            }
        });
    }
}