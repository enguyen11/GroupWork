package com.example.groupwork;

import static com.example.groupwork.Menus.LoadedGameActivity.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.groupwork.GameCreation.GMGameCreation;
import com.example.groupwork.Menus.LoadedGameActivity;
import com.example.groupwork.RPG_Model.Game;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CampaignNotesActivity extends AppCompatActivity {

    private String username;
    private String campaignName;
    private Button notes;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;
    private String TAG = "CampaignNotesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_notes);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("user");
            campaignName = extras.getString("campaignName");
        }
        Log.d(TAG, username);

        notes = findViewById(R.id.btn_notes);

        if(campaignName != null && username != null ) {
            db = FirebaseDatabase.getInstance("https://dndapp-b52b2-default-rtdb.firebaseio.com");
            mDatabase = db.getReference("Users").child(username).child("CampaignList").child(campaignName);

            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    notes.setText(dataSnapshot.child("notes").getValue().toString());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

        }
    }


}