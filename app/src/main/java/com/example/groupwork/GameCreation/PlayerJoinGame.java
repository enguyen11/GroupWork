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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.groupwork.Menus.LoadedGameActivity;
import com.example.groupwork.R;
import com.example.groupwork.RPG_Model.Character;
import com.example.groupwork.RPG_Model.Game;
import com.example.groupwork.RPG_Model.Player;
import com.example.groupwork.RPG_Model.SheetType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import org.checkerframework.checker.units.qual.A;

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
    private String description;
    private ArrayList<String> userCharacters;
    private Game game;

    private FirebaseDatabase db;
    private DatabaseReference userDatabase;
    private DatabaseReference gameDatabase;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_join_game);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("user");
        }

        context = this;
        db = FirebaseDatabase.getInstance("https://dndapp-b52b2-default-rtdb.firebaseio.com");
        userDatabase = db.getReference("Users");
        gameDatabase = db.getReference("Games");

        edittext_campaignName = findViewById(R.id.editText_campaign_name);
        characterSpinner = findViewById(R.id.characterSpinner);
        gameDescription = findViewById(R.id.editText_description);
        saveGame = findViewById(R.id.btn_save);

        selectedCharacter = "default";
        userCharacters = new ArrayList<>();
        //ArrayList<Character> characters = getCharacters();
//        for (Character c: characters) {
//            userCharacters.add(c.getName());
//        }
        //  if(userCharacters == null){
        //    userCharacters.add("Character for testing 2");
        // }


        userDatabase.child(user).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Player p = snapshot.getValue(Player.class);
                ArrayList<Character> cList = p.getCharacters();
                userCharacters = new ArrayList<>();
                for(Character c : cList){
                    userCharacters.add(c.getName());
                }
                userCharacters.add("Create New");

                ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, userCharacters);
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
                        selectedCharacter = "default";
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






        saveGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                campaignName = edittext_campaignName.getText().toString();
                description = gameDescription.getText().toString();
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
        gameDatabase.child(name).addValueEventListener (new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("joinGame", dataSnapshot.toString());
                Log.d("joinGame", dataSnapshot.getValue().toString());
//                game = dataSnapshot.getValue(Game.class);
                ArrayList<String> retrievedParty = new ArrayList<>();
                for(DataSnapshot child : dataSnapshot.child("party").getChildren()){
                    retrievedParty.add(child.getValue().toString());
                }
                //game = new Game(name, dataSnapshot.child("gameMaster").getValue().toString(), dataSnapshot.child("system").getValue().toString(),dataSnapshot.child("numPlayers").getValue(Integer.class), retrievedParty);
                boolean added = game.addPlayer(user, selectedCharacter);
                if(added == true) {
                    gameDatabase.child(campaignName).child("party").child(user).setValue(selectedCharacter);
                    userDatabase.child(user).child("CampaignList").child(campaignName).child("isGM").setValue(false);
                    userDatabase.child(user).child("CampaignList").child(campaignName).child("character").setValue(selectedCharacter);
                    userDatabase.child(user).child("CampaignList").child(campaignName).child("notes").setValue(description);

                    Context context = getApplicationContext();
                    Intent i = new Intent(context, LoadedGameActivity.class);
                    i.putExtra("user", user);
                    i.putExtra("campaignName", campaignName);
                    startActivity(i);
                } else {
                    Toast.makeText(PlayerJoinGame.this,
                            "This game is full, please join another campaign.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled " + error.toString());
            }
        });
    }
}