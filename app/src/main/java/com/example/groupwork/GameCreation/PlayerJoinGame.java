package com.example.groupwork.GameCreation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.groupwork.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PlayerJoinGame extends AppCompatActivity {


    private EditText campaignID;
    private Spinner character;
    private EditText gameDescription;
    private Button saveGame;
    private String user;

    private FirebaseDatabase db;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_join_game);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("user");
        }

        campaignID = findViewById(R.id.picker_numPlayers);
        character = findViewById(R.id.characterSpinner);
        gameDescription = findViewById(R.id.editText_description);
        saveGame = findViewById(R.id.btn_save);

    }
}