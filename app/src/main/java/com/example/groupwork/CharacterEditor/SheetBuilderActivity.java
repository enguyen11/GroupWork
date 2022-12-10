package com.example.groupwork.CharacterEditor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.groupwork.R;
import com.example.groupwork.RPG_Model.Player;
import com.example.groupwork.RPG_Model.SheetType;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SheetBuilderActivity extends AppCompatActivity {

    private Player user;
    private String username;
    RecyclerView infoRecycler;
    ArrayList<String> infoList;
    EditText infoField;
    SheetType newSheet;
    Button addButton;
    Button addStatButton;
    ArrayList<String> statGroupList;
    EditText statGroupField;
    RecyclerView statRecycler;
    HashMap<String, ArrayList<String>> statGroups;
    Button buildSheetButton;
    EditText sheetName;
    FirebaseDatabase db;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet_builder);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        db = FirebaseDatabase.getInstance("https://dndapp-b52b2-default-rtdb.firebaseio.com");
        mDatabase = db.getReference("Users");
        if (extras != null) {
            username = extras.getString("player");
            // Intent goTo = new Intent(SheetBuilderActivity.this, CharacterSheetActivity.class);
            // goTo.putExtra("player", user);
            // startActivity(goTo);
        }
        newSheet = new SheetType();
        infoField = findViewById(R.id.editTextInfoAdd);
        infoList = new ArrayList<>();
        sheetName = findViewById(R.id.editTextSheetName);
        statGroupList = new ArrayList<>();
        statGroups = new HashMap<>(100);
        infoRecycler = findViewById(R.id.recyclerInfoToAdd);
        infoRecycler.setAdapter(new ListBuilderAdapter(this, infoList));
        infoRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        addButton = findViewById(R.id.addInfoButton);
        buildSheetButton = findViewById(R.id.buildSheetButton);
        addButton.setOnClickListener(view -> {
        String info = infoField.getText().toString();
        if(info != null){
            infoList.add(info);
            infoRecycler.getAdapter().notifyDataSetChanged();
            infoField.setText(null);
        }
        });
        statGroupField = findViewById(R.id.editTextStatAdd);
        statRecycler = findViewById(R.id.recyclerStatsToAdd);
        statRecycler.setAdapter(new CategoryListAdapter(this, statGroupList, statGroups));
        statRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        addStatButton = findViewById(R.id.addStatButton);
        addStatButton.setOnClickListener(view -> {
            statGroupList.add(statGroupField.getText().toString());
            ArrayList<String> list = new ArrayList<>();
            statGroups.put(statGroupField.getText().toString(), list);
            statRecycler.getAdapter().notifyDataSetChanged();
            statGroupField.setText(null);
        });
        buildSheetButton.setOnClickListener(view -> {
            newSheet.setStatCats(statGroupList);
            newSheet.setStats(statGroups);
            newSheet.setInfo(infoList);
            newSheet.setName(sheetName.getText().toString());
            //user.getSheets().add(newSheet);
            mDatabase.child(username).child("sheets").push().setValue(newSheet);

        });
    }

    @Override
    public void onBackPressed(){
        Intent back = new Intent();
        back.putExtra("player", user);
        setResult(Activity.RESULT_OK, back);
        super.onBackPressed();

    }
}