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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class DnDChat extends AppCompatActivity {

    private TextInputLayout messageArea;
    //private TextInputLayout recipientArea;
    private FloatingActionButton send;
    public static ArrayList<Message> messageList;
    public static String SENDER;
    public static String CHATNAME;
    public static String CAMPAIGN_NAME;
    private MessageAdapter myMSGAdapter;

    private FirebaseDatabase db;
    private DatabaseReference mDatabase;
    private RecyclerView chatHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dn_dchat);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.SENDER = extras.getString("userID");
            this.CHATNAME = extras.getString("chatName");
            this.CAMPAIGN_NAME = extras.getString("campaignName");
        }
        //System.out.println("Campaign Name: " + CAMPAIGN_NAME);
        //System.out.println("Chat Name: " + CHATNAME);
        setTitle(CHATNAME);
        messageList = new ArrayList<>();
        this.messageArea = findViewById(R.id.messageBar);
        this.send = findViewById(R.id.send);
        //this.recipientArea = findViewById(R.id.receiverBar);
        this.chatHistory = findViewById(R.id.chatHistory);
        this.myMSGAdapter = new MessageAdapter(messageList, chatHistory.getContext());
        this.chatHistory.setAdapter(myMSGAdapter);
        this.chatHistory.setLayoutManager(new LinearLayoutManager(DnDChat.this));

        pullDownChat();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String recipient = recipientArea.getEditText().getText().toString();
                String message_content = messageArea.getEditText().getText().toString();
                Message message = new Message(SENDER, message_content);
                messageList.add(message);

                db = FirebaseDatabase.getInstance("https://dndapp-b52b2-default-rtdb.firebaseio.com/");

                StringBuilder path = new StringBuilder("Games/");
                path.append(CAMPAIGN_NAME);
                path.append("/ChatRoom/");
                path.append(CHATNAME);
                System.out.println("Path: " + path.toString());

                mDatabase = db.getReference(path.toString());
                mDatabase.push().setValue(message);

                //recipientArea.getEditText().getText().clear();
                messageArea.getEditText().getText().clear();
                chatHistory.getAdapter().notifyDataSetChanged();

            }
        });
    }

    private void pullDownChat(){
        StringBuilder path = new StringBuilder("Games/");
        path.append(CAMPAIGN_NAME);
        path.append("/ChatRoom");
        //path.append(CHATNAME);
        //System.out.println(path.toString());
        db = FirebaseDatabase.getInstance("https://dndapp-b52b2-default-rtdb.firebaseio.com/");
        mDatabase = db.getReference(path.toString());
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot mySnapShot: snapshot.getChildren()){
                    System.out.println("Key: " + mySnapShot.getKey());
                    System.out.println("Value: " + mySnapShot.getValue());
                    Message tempMessage = (Message) mySnapShot.getValue(Message.class);
                    messageList.add(tempMessage);
                    myMSGAdapter.notifyDataSetChanged();
                }
                //System.out.println("My List: " + Arrays.toString(chatList.toArray()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });
    }


}