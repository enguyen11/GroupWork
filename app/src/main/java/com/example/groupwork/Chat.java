package com.example.groupwork;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
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
import com.google.firebase.database.ChildEventListener;
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

public class Chat extends AppCompatActivity implements StickerSelectionFragment.OnInputListener {


    private static final String TAG = "ChatActivity";

    private String userID;
    private TextView welcomeMsg;
    private Button friendsButton;
    private Button stickerButton;
    private ArrayList<StickerMessage> stickerMsgList;
    private ArrayList<Sticker> stickersToSend;
    private RecyclerView chatHistoryRecyclerView;
    private RecyclerView  selectedStickerRecyclerView;
    private MessageAdapter stickerMsgAdapter;
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
    private Button messageButton;
    //Notifications
    String channelId;
    NotificationManager notificationManager;

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
        db = FirebaseDatabase.getInstance("https://cs5220-dndapp-default-rtdb.firebaseio.com/");
        mDatabase = db.getReference("Users");
        messageView.setAdapter(new MessageAdapter(messageList, messageView.getContext()));
        messageView.setLayoutManager(new LinearLayoutManager(Chat.this));
        messageView.getAdapter().notifyDataSetChanged();

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


                    //Send message to db
                    sendMessageToFirebase(message);
                    //Setup Notification Channel
                    createNotificationChannel();
                    //Send message to db
                }
            }
        });



        //user's chat history shown in a recyclerview
        stickerMsgList = new ArrayList<>();
        chatHistoryRecyclerView = findViewById(R.id.chat_history_recyclerview);
        stickerMsgAdapter = new MessageAdapter(stickerMsgList, this);
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
                stickerMsgList.clear();
                for(DataSnapshot child : snapshot.getChildren()){

                    String sender = child.child("sender").getValue().toString();
                    String receiver = child.child("receiver").getValue().toString();
                    String content = child.child("content").getValue().toString();
                    StickerMessage message = new StickerMessage(sender, receiver, content);
                    stickerMsgList.add(message);
                }
                messageView.getAdapter().notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        /*
        SETTING UP NOTIFCATION CHANNEL
         */
        createNotificationChannel();

        db.getReference().child("Users").child(userID).child("messageList").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("notification", "A child has been added");
                Iterable<DataSnapshot> messages = snapshot.child("Users").child(userID).child("messageList").getChildren();
                sendChatNotificationSimplified();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /**
     * This method creates a notification channel and listener to trigger
     * the notification when a new message is received.
     */
    private void createNotificationChannel() {
        // Check the version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && this.notificationManager == null) {
            CharSequence title = "You got mail!";
            String channelId = "STANDARD_NOTIFICATION_CHANNEL";
            this.channelId = channelId;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel currentChannel = new NotificationChannel(channelId, title, importance);
            String description = "A new message has arrived.";
            currentChannel.setDescription(description);
            currentChannel.setLightColor(Color.blue(120));

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(currentChannel);
            this.notificationManager = notificationManager;
        }
    }

    public void sendChatNotificationSimplified() {
        //Intent activityIntent = new Intent(this, Chat.class);
        //PendingIntent contentIntent = PendingIntent.getActivity(this,
        //       0, activityIntent, 0);
        // might meed to build a back stack for the pending intent

        int stickerID = Chat.this.getResources().getIdentifier("sticker_d12","drawable", Chat.this.getPackageName());

        Bitmap picture = BitmapFactory.decodeResource(getResources(), stickerID);

        Notification notification = new NotificationCompat.Builder(this, this.channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Sticker Received!")
                .setContentText("You got a message incoming ")
                .setLargeIcon(picture)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(picture)
                        .bigLargeIcon(null)) // this makes the thumbnail vanish on the drag
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                //        .setContentIntent(contentIntent) Not sure yet what we want to launch
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setLights(Color.RED, 300, 100)
                .setVibrate(new long[] { 0, 1000, 100, 1000, 100 })
                .build();

        notificationManager.notify(1, notification);
        // will need to change the ID peram if we want multiple notifications to show up at a time
        // ideally, might be the id of the hash created in the db?
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
        Log.d("Before Update", "*******************************************" + stickersToSend.size());
        System.out.println(stickersToSend.get(0).getName());
        stickerToSendAdapter.update(stickersToSend);
    }

    public ArrayList<Sticker> getSelectedStickers() {
        return stickersToSend;
    }


    private void notifyErrorToast(String msg) {
        Log.d(TAG, msg);
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}