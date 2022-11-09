package com.example.groupwork;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.data.FirebaseViewModel;
import com.example.groupwork.model.Dnd5eItem;
import com.example.groupwork.model.ItemAdapter;
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
import java.util.List;
import java.util.Map;

public class Chat extends AppCompatActivity implements StickerSelectionFragment.OnInputListener{


    private static final String TAG = "ChatActivity";

    private String userID;
    private TextView welcomeMsg;
    private Button friendsButton;
    private Button stickerButton;
    private ArrayList<Sticker> stickerMsgList;
    private ArrayList<Sticker> stickersToSend;
    private RecyclerView chatHistoryRecyclerView;
    private RecyclerView  selectedStickerRecyclerView;
    private StickerMessageAdapter stickerMsgAdapter;
    private StickerMessageAdapter stickerToSendAdapter;
    private ArrayList<StickerMessage> userMessages;
    private ArrayList<StickerMessage> receiverMessages;
    //private String receiver;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;
    private String friendID;
    private FirebaseViewModel viewModel;
    private ArrayList<StickerMessage> messageList;
    private RecyclerView messageView;
    private ArrayList<String> displayList;
    private Button messageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_account);

        messageView = findViewById(R.id.chat_history_recyclerview);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userID = extras.getString("userID");
        }
        messageList = new ArrayList<>();
        displayList = new ArrayList<>();
        displayList.add("Dummy");
        db = FirebaseDatabase.getInstance("https://cs5220-dndapp-default-rtdb.firebaseio.com/");
        mDatabase = db.getReference("Users");
        messageView.setAdapter(new MessageAdapter(messageList, messageView.getContext()));
        messageView.setLayoutManager(new LinearLayoutManager(Chat.this));
        messageView.getAdapter().notifyDataSetChanged();

        welcomeMsg = findViewById(R.id.textView_welcome_stickers);

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

        messageButton = findViewById(R.id.button_messages);
        messageButton.setOnClickListener(view -> {
            Intent goToMessages = new Intent(Chat.this, Messages.class);
            goToMessages.putExtra("userID", userID);
            Chat.this.startActivity(goToMessages);
        });



        friendsButton = findViewById(R.id.button_friends);
        friendsButton.setOnClickListener(view -> {
            Intent goToFriends = new Intent(Chat.this, Friends.class);
            goToFriends.putExtra("userID", userID);
            Chat.this.startActivity(goToFriends);
        });


        FirebaseDatabase db = FirebaseDatabase.getInstance("https://cs5220-dndapp-default-rtdb.firebaseio.com/");
        this.mDatabase = db.getReference("Users");

        userMessages = new ArrayList<>();
        receiverMessages = new ArrayList<>();
        db = FirebaseDatabase.getInstance("https://cs5220-dndapp-default-rtdb.firebaseio.com/");
        mDatabase = db.getReference("Users");


        extras = getIntent().getExtras();
        if (extras != null) {
            userID = extras.getString("userID");
            welcomeMsg.setText("Welcome, " + userID);

        } else {
            welcomeMsg.setText("Welcome!");
        }


        stickerButton = (Button) findViewById(R.id.select_stickers);
        stickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStickerSelection(v);
//                getSupportFragmentManager().beginTransaction()
//                        .setReorderingAllowed(true)
//                        .add(R.id.fragment_container_view, StickerSelectionFragment.class, null)
//                        .addToBackStack(null)
//                        .commit();

            }
        });

        Button sendButton = (Button) findViewById(R.id.send_button);
        //friendID = "";
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText mEdit = (EditText) findViewById(R.id.sendToUser);
                friendID = mEdit.getText().toString();
                if(stickersToSend.size() == 0) {
                    notifyErrorToast("Please select stickers");
                }
                else if(friendID.length() == 0) {
                    notifyErrorToast("Please specify user to send sticker to.");
                } else {
                    if(stickersToSend.size() > 1) {
                        notifyErrorToast("Warning: only the first sticker will display in chat history");
                    }


                /*
                mDatabase.get() sends us to onComplete()
                onComplete() will populate the ArrayLists with the data from the db
                Each time this button is clicked they should be reset
                 */
                    mDatabase.child(userID).child("messageList").get();
                    mDatabase.child(friendID).child("messageList").get();
//                    mEdit = (EditText) findViewById(R.id.message);
                    String message = "";
/*
                if (stickersToSend.size() > 0) {
                    for (Sticker s : stickersToSend) {
                        mDatabase.push().child(userID).child("messageList").child(friendID).child("messages").setValue(s);
                        //sendMessageToFirebase(friendID,message);
                    }
                }

 */

                    mDatabase.child(userID).child("messageList").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        /**
                         * This takes the ArrayLists and repopulates them with messages from the db
                         * Messages have three parts, hence the String[3]
                         *
                         * @param task
                         */
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            userMessages.clear();
                            String[] array = new String[3];
                            int num = 0;
                            Iterable<DataSnapshot> outer = task.getResult().getChildren();
                            for (DataSnapshot inner : outer) {
                                for (DataSnapshot part : inner.getChildren()) {
                                    array[num] = part.getValue().toString();
                                    num++;
                                }
                                num = 0;
                                StickerMessage message = new StickerMessage(array[2], array[1], array[0]);
                                userMessages.add(message);
                            }
                        }
                    });
                    mDatabase.child(friendID).child("messageList").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        /**
                         * Do the above again for the friend's message list
                         *
                         * @param task
                         */
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            receiverMessages.clear();
                            String[] array = new String[3];
                            int num = 0;
                            Iterable<DataSnapshot> outer = task.getResult().getChildren();
                            for (DataSnapshot inner : outer) {
                                for (DataSnapshot part : inner.getChildren()) {
                                    array[num] = part.getValue().toString();
                                    num++;
                                }
                                num = 0;
                                StickerMessage message = new StickerMessage(array[2], array[1], array[0]);
                                receiverMessages.add(message);
                            }
                        }
                    });


                    //Send message to db
                    sendMessageToFirebase(message);
                }
            }
        });



        //user's chat history shown in a recyclerview
        stickerMsgList = new ArrayList<>();
        chatHistoryRecyclerView = findViewById(R.id.chat_history_recyclerview);
        stickerMsgAdapter = new StickerMessageAdapter(stickerMsgList, this);
        chatHistoryRecyclerView.setAdapter(stickerMsgAdapter);
        chatHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        stickersToSend = new ArrayList<>();
        selectedStickerRecyclerView = findViewById(R.id.selected_sticker_recyclerView);
        stickerToSendAdapter = new StickerMessageAdapter( stickersToSend, this);
        selectedStickerRecyclerView.setAdapter(stickerToSendAdapter);
        selectedStickerRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));

        mDatabase.child(userID).child("messageList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()){
                    displayList.add("Sent by: " + child.child("sender").getValue().toString() + "\nReceived by: " +
                            child.child("receiver").getValue().toString() + "\n" + child.child("content").getValue().toString());
                    messageView.getAdapter().notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }

    public void openStickerSelection(View view) {
        new StickerSelectionFragment().show(getSupportFragmentManager(), StickerSelectionFragment.TAG);
    }

    /**
     * Pushes message to user and friend's messageLists
     *
     * @param message
     */
    private void sendMessageToFirebase(String message) {
        String string = message;
        for(Sticker image : stickersToSend){
            string += " " + image.getName();
        }
        StickerMessage newMessage = new StickerMessage(userID, friendID, string);
        //getList();

        userMessages.add(newMessage);
        receiverMessages.add(newMessage);
        mDatabase.child(userID).child("messageList").push().setValue(newMessage);
        mDatabase.child(friendID).child("messageList").push().setValue(newMessage);
        stickerToSendAdapter.clear();
    }


    private ArrayList getMessageHistory(String sendID) {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://cs5220-dndapp-default-rtdb.firebaseio.com/");
        DatabaseReference mDatabase = db.getReference("Users");

        ArrayList<String> arr = new ArrayList<>();
        mDatabase.child(userID).child("messageList").child(friendID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            /**
             * This takes the ArrayLists and repopulates them with messages from the db
             * Messages have three parts, hence the String[3]
             * @param task
             */
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> outer = task.getResult().getChildren();
                for (DataSnapshot inner : outer) {
                    for (DataSnapshot part : inner.getChildren()) {
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
        stickersToSend = selected_stickerList;
        for (Sticker s : stickersToSend) {
            s.setSender(userID);
        }
        Log.d("Before Update", "" + stickersToSend.size());
        stickerToSendAdapter.update(stickersToSend);
    }

    public ArrayList<Sticker> getSelectedStickers() {
        return stickersToSend;
    }

    private void getList(){
        mDatabase.child(userID).child("messageList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()){
                    displayList.add("Sent by: " + child.child("sender").getValue().toString() + "\nReceived by: " +
                            child.child("receiver").getValue().toString() + "\n" + child.child("content").getValue().toString());
                    messageView.getAdapter().notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void notifyErrorToast(String msg) {
        Log.d(TAG, msg);
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}