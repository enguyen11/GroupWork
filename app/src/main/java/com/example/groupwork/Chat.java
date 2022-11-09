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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_account);


        welcomeMsg = findViewById(R.id.textView_welcome_stickers);


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


        Bundle extras = getIntent().getExtras();
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

        Button mButton = (Button) findViewById(R.id.send_button);
        //friendID = "";
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                EditText mEdit = (EditText) findViewById(R.id.sendToUser);
                friendID = mEdit.getText().toString();

                /*
                mDatabase.get() sends us to onComplete()
                onComplete() will populate the ArrayLists with the data from the db
                Each time this button is clicked they should be reset
                 */
                mDatabase.child(userID).child("messageList").get();
                mDatabase.child(friendID).child("messageList").get();
                mEdit = (EditText) findViewById(R.id.message);
                String message = mEdit.getText().toString();
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
        });



        //user's chat history shown in a recyclerview
        stickerMsgList = new ArrayList<>();
        chatHistoryRecyclerView = findViewById(R.id.chat_history_recyclerview);
        stickerMsgAdapter = new StickerMessageAdapter(stickerMsgList, this);
        chatHistoryRecyclerView.setAdapter(stickerMsgAdapter);
        chatHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        stickersToSend = new ArrayList<>();

        selectedStickerRecyclerView = findViewById(R.id.selected_sticker_recyclerView);
        stickerToSendAdapter = new StickerMessageAdapter(stickersToSend, this);
        selectedStickerRecyclerView.setAdapter(stickerToSendAdapter);
        selectedStickerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        selectedStickerRecyclerView.setHasFixedSize(true);


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
        StickerMessage newMessage = new StickerMessage(userID, friendID, message);
        //getList();
        userMessages.add(newMessage);
        receiverMessages.add(newMessage);
        mDatabase.child(userID).child("messageList").push().setValue(newMessage);
        mDatabase.child(friendID).child("messageList").push().setValue(newMessage);
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
        stickerToSendAdapter.update(stickersToSend);
    }

    public ArrayList<Sticker> getSelectedStickers() {
        return stickersToSend;
    }



}