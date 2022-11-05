package com.example.groupwork;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Chat extends AppCompatActivity {

    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

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

}