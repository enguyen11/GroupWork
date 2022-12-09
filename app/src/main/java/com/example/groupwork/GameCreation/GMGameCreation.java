package com.example.groupwork.GameCreation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

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
    private DatabaseReference mDatabase;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_creation);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Log.d(TAG, extras.getString("user"));
            user = extras.getString("user");
        }

        db = FirebaseDatabase.getInstance("https://dndapp-b52b2-default-rtdb.firebaseio.com");
        mDatabase = db.getReference("Users");

        edittext_campaignName = findViewById(R.id.editText_campaignName);
        gameSystemSpinner = findViewById(R.id.characterSpinner);
        picker_numPlayers = findViewById(R.id.picker_numPlayers);
        picker_numPlayers.setMinValue(0);
        picker_numPlayers.setMaxValue(10);
        gameDescription = findViewById(R.id.editText_description);
        saveGame = findViewById(R.id.btn_save);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.available_systems, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameSystemSpinner.setAdapter(adapter);
        gameSystemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String text;
                text = adapterView.getItemAtPosition(position).toString();
                Log.d(TAG, "onItemSelected: " + text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                gameSystemSpinner.setPrompt(getString(R.string.categories));
                selectedSystem = null;
            }
        });

        selectedSystem = null;


        saveGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                campaignName = edittext_campaignName.getText().toString();
                numPlayers = picker_numPlayers.getValue();
                game = new Game(campaignName, user, selectedSystem, numPlayers);
                if(user != null){
                    mDatabase.child("User").child(user).child("games").setValue(game);
                }
                if(user != null){
                    mDatabase.child("User").child(user).child("gameRole").push().setValue(true);
                }

            }

        });
    }


    public void onRestoreInstanceState(Bundle savedInstanceState){
        campaignName = savedInstanceState.getString("campaignName");
        numPlayers = savedInstanceState.getInt("numPlayers");
        selectedSystem = savedInstanceState.getString("system");
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("campaignName",campaignName);
        savedInstanceState.putInt("numPlayers",numPlayers);
        savedInstanceState.putString("system",selectedSystem);
    }
}