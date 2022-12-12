package com.example.groupwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.groupwork.StickerActivity.StickerMessage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PartySummaryActivity extends AppCompatActivity {

    private String username;
    private String campaignName;
    private TextView partyText;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;
    private String TAG = "PartySummaryActivity";
    private String partySummary = "Your party is empty.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_summary);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("user");
            campaignName = extras.getString("campaignName");
        }

        partyText = findViewById(R.id.party_text);

        if(campaignName != null && username != null ) {
            db = FirebaseDatabase.getInstance("https://dndapp-b52b2-default-rtdb.firebaseio.com");
            mDatabase = db.getReference("Games").child(campaignName);

            if(mDatabase != null) {
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        partySummary = "Game Master: " + snapshot.child("gameMaster").getValue().toString() + "\n";
//                    partyText.setText(dataSnapshot.child("notes").getValue().toString());
                        for(DataSnapshot child : snapshot.child("party").getChildren()){
                            partySummary += child.getKey() + " as " + child.getValue().toString() + "\n";
                            Log.d(TAG, "should add " + child.getKey() + " and " + child.getValue().toString() );
                        }

                        partyText.setText(partySummary);
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }

                });

            } else {

                Log.d(TAG, "database is null");
            }

            Log.d(TAG, "hello: " + partySummary);


        }
    }
}