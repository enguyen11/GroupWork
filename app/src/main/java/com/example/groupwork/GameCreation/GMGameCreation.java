package com.example.groupwork.GameCreation;

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

import com.example.groupwork.Login.Login;
import com.example.groupwork.R;
import com.example.groupwork.RPG_Model.Game;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        picker_numPlayers.setMinValue(0);
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
                numPlayers = picker_numPlayers.getValue();
                game = new Game(campaignName, user, selectedSystem, numPlayers);
                game.setDescr(gameDescription.getText().toString());
                if(user != null){
                    if(campaignName == "" || campaignName == null)  {
                        Toast.makeText(GMGameCreation.this,
                                "Campaign Name required", Toast.LENGTH_SHORT).show();
                    } else if(numPlayers == 0) {
                        Toast.makeText(GMGameCreation.this,
                                "Campaign must have a party of size 1 to 10", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        userDatabase.child(user).child("games").child(campaignName).child("isGM").setValue(true); //add game to player
                        gameDatabase.child(campaignName).setValue(game);
                        Context context = getApplicationContext();
                        Intent i = new Intent(context, GMGameCreation.class);
                    }
                } else {
                    Log.e(TAG, "user is null");
                }
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