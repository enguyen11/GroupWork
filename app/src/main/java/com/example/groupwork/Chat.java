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

        Button mButton = (Button) findViewById(R.id.send_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText mEdit = (EditText) findViewById(R.id.sendToUser);
                friendID = mEdit.getText().toString();
                //Todo************* Problem starts here **********************
                /*
                mDatabase.get() sends us to onComplete()
                onComplete() will populate the ArrayLists with the data from the db
                The problem is that the db gets updated with the new message BEFORE onComplete() finishes
                The first time sending a message to a friend this causes a problem, but it seems
                to work okay if you send that message twice
                 */
                mDatabase.child(userID).child("messageList").get(); // gets us to onComplete()
                mDatabase.child(friendID).child("messageList").get();
                mEdit = (EditText) findViewById(R.id.message);
                String message = mEdit.getText().toString();


                //Todo**************** The bulk of the problem ******************
                /*
                This is the code that doesn't seem to be finishing on time
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
                        for(DataSnapshot inner : outer){
                            for(DataSnapshot part : inner.getChildren()){
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
                        for(DataSnapshot inner : outer){
                            for(DataSnapshot part : inner.getChildren()){
                                array[num] = part.getValue().toString();
                                num++;
                            }
                            num = 0;
                            StickerMessage message = new StickerMessage(array[2], array[1], array[0]);
                            receiverMessages.add(message);
                        }
                    }
                });
                //Todo********************The other part**********************
                /*
                Even though the call to sendMessageToFirebase() comes after in the code
                it runs before the onComplete().
                 */
                sendMessageToFirebase(message);
            }
        });
    }

    //Todo*************The end***************
    /*
    This code works perfectly as is. It's just supposed to run AFTER the ArrayLists are updated
    But it runs before instead.
     */
    /**
     * Sets the value of the user and friend message lists to their respective ArrayLists
     * The ArrayLists are meant to be populated with the messages from the db before adding the new one
     * @param message
     */
    private void sendMessageToFirebase(String message) {
        StickerMessage newMessage = new StickerMessage(userID, friendID, message);
        //getList();
        userMessages.add(newMessage);
        receiverMessages.add(newMessage);
        mDatabase.child(userID).child("messageList").setValue(userMessages);
        mDatabase.child(friendID).child("messageList").setValue(receiverMessages);
    }


}