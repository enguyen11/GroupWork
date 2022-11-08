package com.example.groupwork;

import static com.example.groupwork.App.CHANNEL_ID;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Chat extends AppCompatActivity {


    String userID;
    Button friendsButton;
    NotificationManagerCompat notifManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_account);

        notifManager = NotificationManagerCompat.from(this);

        friendsButton = findViewById(R.id.button_friends);

        friendsButton.setOnClickListener(view -> {
            //Open friends activity

        });

        FirebaseDatabase db = FirebaseDatabase.getInstance("https://cs5220-dndapp-default-rtdb.firebaseio.com/");
        DatabaseReference mDatabase = db.getReference("conversation");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userID = extras.getString("userID");
        }

        Button mButton = (Button) findViewById(R.id.send_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendChatNotification(view, "sticker_nolens_dog_demo", "Nolen");

                EditText mEdit = (EditText) findViewById(R.id.sendToUser);
                String friendID = mEdit.getText().toString();

                mEdit = (EditText) findViewById(R.id.message);
                String message = mEdit.getText().toString();

                //mDatabase.push().child("conversations").child(userID).child("chats").child(friendID).child("messages").setValue(message);
                //sendMessageToFirebase(friendID,message);


            }
        });
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

    public void sendChatNotification(View v, String sticker, String sender) {
        //Intent activityIntent = new Intent(this, Chat.class);
        //PendingIntent contentIntent = PendingIntent.getActivity(this,
        //       0, activityIntent, 0);
        // might meed to build a back stack for the pending intent

        int stickerID = Chat.this.getResources().getIdentifier(sticker,"drawable", Chat.this.getPackageName());

        Bitmap picture = BitmapFactory.decodeResource(getResources(), stickerID);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.sticker_barbarian_demo)
                .setContentTitle("Sticker Received!")
                .setContentText("You got a sticker from " + sender)
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

        notifManager.notify(1, notification);
        // will need to change the ID peram if we want multiple notifications to show up at a time
        // ideally, might be the id of the hash created in the db?
    }

}