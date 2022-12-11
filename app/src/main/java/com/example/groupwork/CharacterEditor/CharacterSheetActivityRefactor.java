package com.example.groupwork.CharacterEditor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TableLayout;

import com.example.groupwork.R;
import com.example.groupwork.RPG_Model.Player;
import com.example.groupwork.RPG_Model.Resource;
import com.example.groupwork.RPG_Model.SheetType;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class CharacterSheetActivityRefactor extends AppCompatActivity {

    // CHARACTER DATA POINTS
    private Player user;
    private String username;
    private SheetType sheetType;
    private String sheetName;
    private ArrayList<String> infoFields;
    private HashMap<String, ArrayList<String>> stats;
    private HashMap<String, Resource> resources;
    private ArrayList<EditText> infoViews;
    private ArrayList<EditText> statViews;
    private ArrayList<EditText> resourceViews;
    private ConstraintLayout layout;
    private RecyclerView infoRecycler;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;
    private Context context;
    private TableLayout infoTable;
    private TableLayout statTable;


    // UI links
    private EditText name_ui;
    private EditText desc_ui;
    private EditText race_ui;
    private EditText class_ui;
    private RecyclerView recycler_ui;
    private CharacterSheetRecycler adapter;


    //stats data
    private ArrayList<SheetStatCard> sheetStatCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //------------------SETUP----------------------------
        // view setup
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_sheet_refactor);
        // data base setup
        db = FirebaseDatabase.getInstance("https://dndapp-b52b2-default-rtdb.firebaseio.com");
        mDatabase = db.getReference("Users");
        // intent and bundle
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        // get userName
        if (extras != null) {
            username = extras.getString("player");
        }

        // link the UI
        name_ui = findViewById(R.id.charaterName);
        desc_ui = findViewById(R.id.description);
        race_ui = findViewById(R.id.race);
        class_ui = findViewById(R.id.classSheet);
        recycler_ui = findViewById(R.id.statRecycler);


        // set the recycler
        sheetStatCards = new ArrayList<>();
        // sheetStatCard can be used to define what ever you are asking the user
        sheetStatCards.add(new SheetStatCard("uniqueID", "Example data", R.drawable.sticker_bloodhunter, false));
        adapter = new CharacterSheetRecycler(sheetStatCards);
        recycler_ui.setAdapter(adapter);

        // update list, when you are done you can use:
        recycler_ui.getAdapter().notifyDataSetChanged();
    }
}