package com.example.groupwork.GameCreation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.groupwork.DNDChat.Message;
import com.example.groupwork.Login.Login;
import com.example.groupwork.Menus.LoadedGameActivity;
import com.example.groupwork.R;
import com.example.groupwork.RPG_Model.Game;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GMGameCreation extends AppCompatActivity {

    private String TAG = "GMGameCreation";

    private EditText edittext_campaignName;
    private Spinner gameSystemSpinner;
    private String selectedSystem;
    private NumberPicker picker_numPlayers;
    private EditText gameDescription;
    private Button saveGame;
    private String user;
    private String campaignName;
    private int numPlayers;
    private String descr;

    private FirebaseDatabase db;
    private DatabaseReference userDatabase;
    private DatabaseReference gameDatabase;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_creation);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("user");
        }

        db = FirebaseDatabase.getInstance("https://dndapp-b52b2-default-rtdb.firebaseio.com");
        userDatabase = db.getReference("Users");
        gameDatabase = db.getReference("Games");

        edittext_campaignName = findViewById(R.id.editText_campaignName);
        gameSystemSpinner = findViewById(R.id.characterSpinner);
        picker_numPlayers = findViewById(R.id.picker_numPlayers);
        picker_numPlayers.setMinValue(1);
        picker_numPlayers.setMaxValue(10);
        gameDescription = findViewById(R.id.editText_description);
        saveGame = findViewById(R.id.btn_save);

        selectedSystem = null;

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.available_systems, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameSystemSpinner.setAdapter(adapter);
        gameSystemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String text;
                selectedSystem = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                gameSystemSpinner.setPrompt(getString(R.string.categories));
                selectedSystem = adapterView.getItemAtPosition(0).toString();
            }
        });



        saveGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                campaignName = edittext_campaignName.getText().toString();
                createGame(campaignName);

            }
        });
    }

    private void createGame(String name) {
        gameDatabase.child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Toast.makeText(GMGameCreation.this,
                            "Campaign Name already exists", Toast.LENGTH_SHORT).show();
                }else{
                    numPlayers = picker_numPlayers.getValue();
                    game = new Game(campaignName, user, selectedSystem, numPlayers);
                    descr = gameDescription.getText().toString();
                    if(user != null){
                        if(campaignName == "" || campaignName == null)  {
                            Toast.makeText(GMGameCreation.this,
                                    "Campaign Name required", Toast.LENGTH_SHORT).show();
                        } else if(numPlayers == 0) {
                            Toast.makeText(GMGameCreation.this,
                                    "Campaign must have a party of size 1 to 10", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            game.setDescr(descr);
                            userDatabase.child(user).child("CampaignList").child(campaignName).child("isGM").setValue(true); //add game to player
                            userDatabase.child(user).child("CampaignList").child(campaignName).child("character").setValue("Game Master");
                            userDatabase.child(user).child("CampaignList").child(campaignName).child("notes").setValue(descr);
                            gameDatabase.child(campaignName).setValue(game);

                            DatabaseReference mDatabase2 = db.getReference("Games");
                            Message systemMsg1 = new Message("System", "This is the start to the In-Game Chat");
                            Message systemMsg2 = new Message("System", "This is the start to the Out-of-Game Chat");
                            mDatabase2.child(campaignName).child("ChatRoom")
                                    .child("In-Game Chat").push().setValue(systemMsg1);
                            mDatabase2.child(campaignName).child("ChatRoom")
                                    .child("Out-of-Game Chat").push().setValue(systemMsg2);
                            Context context = getApplicationContext();
                            Intent i = new Intent(context, LoadedGameActivity.class);
                            i.putExtra("user", user);
                            i.putExtra("campaignName", campaignName);
                            startActivity(i);
                        }
                    } else {
                        Log.e(TAG, "user is null");
                        Toast.makeText(GMGameCreation.this,
                                "Unable to create game. ", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, databaseError.getMessage());
                Toast.makeText(GMGameCreation.this,
                        "Unable to create game.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void onRestoreInstanceState(Bundle savedInstanceState){
        campaignName = savedInstanceState.getString("campaignName");
        numPlayers = savedInstanceState.getInt("numPlayers");
        selectedSystem = savedInstanceState.getString("system");
        descr = savedInstanceState.getString("description");
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        campaignName = edittext_campaignName.getText().toString();
        numPlayers = picker_numPlayers.getValue();
        descr = gameDescription.getText().toString();
        savedInstanceState.putString("campaignName",campaignName);
        savedInstanceState.putInt("numPlayers",numPlayers);
        savedInstanceState.putString("system",selectedSystem);
        savedInstanceState.putString("description",descr);
    }
}