package com.example.groupwork;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.model.Dnd5eItem;
import com.example.groupwork.model.ItemAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Chat extends AppCompatActivity implements StickerSelectionFragment.OnInputListener{


    private static final String TAG = "ChatActivity";

    private String userID;
    private TextView welcomeMsg;
    private Button friendsButton;
    private Button stickerButton;
    private ArrayList<Sticker> stickerMsgList;
    private RecyclerView chatHistoryRecyclerView;
    private StickerMessageAdapter stickerMsgAdapter;

    private String friendID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_account);

        welcomeMsg = findViewById(R.id.textView_welcome_stickers);

        friendsButton = findViewById(R.id.button_friends);

        friendsButton.setOnClickListener(view -> {
            Intent goToFriends = new Intent(Chat.this, Friends.class);
            Chat.this.startActivity(goToFriends);
        });

        FirebaseDatabase db = FirebaseDatabase.getInstance("https://cs5220-dndapp-default-rtdb.firebaseio.com/");
        DatabaseReference mDatabase = db.getReference("conversation");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userID = extras.getString("userID");
        }

        welcomeMsg.setText("Welcome, " + userID);

        stickerButton = (Button) findViewById(R.id.select_stickers);
        stickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_show_sticker);
                if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .add(R.id.fragment_container_view, StickerSelectionFragment.class, null)
                            .commit();
                }
            }
        });

        Button mButton = (Button) findViewById(R.id.send_button);
        friendID = "";
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText mEdit = (EditText) findViewById(R.id.sendToUser);
                friendID = mEdit.getText().toString();

                mEdit = (EditText) findViewById(R.id.message);
                String message = mEdit.getText().toString();

                //mDatabase.push().child("conversations").child(userID).child("chats").child(friendID).child("messages").setValue(message);
                //sendMessageToFirebase(friendID,message);

            }
        });

        //user's chat history shown in a recyclerview
        stickerMsgList = new ArrayList<>();

        chatHistoryRecyclerView = findViewById(R.id.chat_history_recyclerview);

        stickerMsgAdapter = new StickerMessageAdapter(stickerMsgList, this);
        chatHistoryRecyclerView.setAdapter(stickerMsgAdapter);
        chatHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    private void sendMessageToFirebase(String sendID, String message) {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://cs5220-dndapp-default-rtdb.firebaseio.com/");
        DatabaseReference mDatabase = db.getReference("conversation");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //mDatabase.child("conversations").child(userID).child("chats").child(sendID).child("messages").setValue(message);
                //mDatabase.child("conversations").child(userID).child("chats").child(sendID).child("ifSender").setValue(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Chat.this, "Fail to send " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList getMessageHistory(String sendID) {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://cs5220-dndapp-default-rtdb.firebaseio.com/");
        DatabaseReference mDatabase = db.getReference("Users");

//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                //mDatabase.child("conversations").child(userID).child("chats").child(sendID).child("messages").setValue(message);
//                //mDatabase.child("conversations").child(userID).child("chats").child(sendID).child("ifSender").setValue(true);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(Chat.this, "Fail to send " + error, Toast.LENGTH_SHORT).show();
//            }
//        });
        ArrayList<String> arr = new ArrayList<>();
        mDatabase.child(userID).child("messageList").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            /**
             * This takes the ArrayLists and repopulates them with messages from the db
             * Messages have three parts, hence the String[3]
             * @param task
             */
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> outer = task.getResult().getChildren();
                for(DataSnapshot inner : outer){
                    for(DataSnapshot part : inner.getChildren()){
                        arr.add(part.getValue().toString());
                        Log.d(TAG, part.getValue().toString());
                    }
                }
            }
        });
        return arr;
    }

    @Override
    public void sendInput(ArrayList<Sticker> selected_stickerList) {
        stickerMsgList = selected_stickerList;

        for (Sticker s : stickerMsgList ) {
            Log.d("STICKERS NAMEEEEEEEEEE", s.getName());
        }

        stickerMsgAdapter = new StickerMessageAdapter(stickerMsgList, this);
        chatHistoryRecyclerView.setAdapter(stickerMsgAdapter);
        chatHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}