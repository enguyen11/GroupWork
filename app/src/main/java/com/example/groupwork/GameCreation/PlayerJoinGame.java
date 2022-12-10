package com.example.groupwork.GameCreation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.groupwork.R;
import com.example.groupwork.RPG_Model.Character;
import com.example.groupwork.RPG_Model.Game;
import com.example.groupwork.RPG_Model.SheetType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlayerJoinGame extends AppCompatActivity {

    private String TAG = "PlayerJoinGame";

    private EditText edittext_campaignName;
    private Spinner characterSpinner;
    private EditText gameDescription;
    private Button saveGame;
    private String user;

    private String campaignName;
    private String selectedCharacter;
    private ArrayList<String> userCharacters;
    private Game game;

    private FirebaseDatabase db;
    private DatabaseReference userDatabase;
    private DatabaseReference gameDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_join_game);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("user");
        }

        db = FirebaseDatabase.getInstance("https://dndapp-b52b2-default-rtdb.firebaseio.com");
        userDatabase = db.getReference("Users");
        gameDatabase = db.getReference("Games");

        edittext_campaignName = findViewById(R.id.editText_campaign_name);
        characterSpinner = findViewById(R.id.characterSpinner);
        gameDescription = findViewById(R.id.editText_description);
        saveGame = findViewById(R.id.btn_save);

        selectedCharacter = null;
        userCharacters = new ArrayList<>();
        //ArrayList<Character> characters = getCharacters();
//        for (Character c: characters) {
//            userCharacters.add(c.getName());
//        }
        if(userCharacters == null){
            userCharacters.add("Character for testing 2");
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, userCharacters);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        characterSpinner.setAdapter(adapter);
        characterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedCharacter = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                characterSpinner.setPrompt(getString(R.string.categories));
                selectedCharacter = null;
            }
        });



        saveGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                campaignName = edittext_campaignName.getText().toString();
                joinGame(campaignName);
//                Log.d(TAG, "onclick " + game.getName());
//                if(game == null || game.getName() != campaignName) {
//                    Toast.makeText(PlayerJoinGame.this,
//                            "Please have your GM create your campaign before you join.", Toast.LENGTH_SHORT).show();
//                } else if (selectedCharacter == null){
//                    Toast.makeText(PlayerJoinGame.this,
//                            "Please select a character for your campaign.", Toast.LENGTH_SHORT).show();
//                } else {
//                    game.addPlayer(user);
//                    game.addCharacter(selectedCharacter);
//                    userDatabase.child(user).child("games").child(campaignName).child("isGM").setValue(false);
//                }
            }
        });

    }

    private ArrayList getCharacters() {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://dndapp-b52b2-default-rtdb.firebaseio.com");
        DatabaseReference mDatabase = db.getReference("Users");

        ArrayList<String> arr = new ArrayList<>();
        mDatabase.child(user).child("Characters").addValueEventListener (new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            arr.add(postSnapshot.getValue().toString());
                            Log.d(TAG, postSnapshot.getValue().toString());
                        }
                    }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, error.toString());
            }
        });
        return arr;
    }

    private void joinGame(String name) {
        gameDatabase.addListenerForSingleValueEvent (new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                game = dataSnapshot.getValue(Game.class);
                Log.d(TAG, "join game " + game.getName());

                game.addPlayer(user);
//                game.addCharacter(selectedCharacter);
                gameDatabase.child(campaignName).child("partyPlayers").child(user).setValue(user);
                userDatabase.child(user).child("games").child(campaignName).child("isGM").setValue(false);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled " + error.toString());
            }
        });
    }
}

