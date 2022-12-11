package com.example.groupwork.CharacterEditor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.groupwork.R;
import com.example.groupwork.RPG_Model.Character;
import com.example.groupwork.RPG_Model.Player;
import com.example.groupwork.RPG_Model.Resource;
import com.example.groupwork.RPG_Model.SheetType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


public class CharacterSheetActivity extends AppCompatActivity {
    private Player user;
    private String username;
    private SheetType sheetType;
    private String sheetName;
    private ArrayList<String> infoFields;
    private HashMap<String, ArrayList<String>> stats;
    private ArrayList<String> statCats;
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
    private TableLayout resourceTable;
    private RecyclerView statRec;
    private int sheetNum;
    private ArrayList<String> infoVals;
    private ArrayList<ArrayList<String>> statVals;
    private String charName;
    private Character character;
    private boolean isTemplate;
    private Button saveButton;
    private int charIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("CHARACTER SHEET", "onCreate: ON CHARACTER SHEET ACTIVITY");
        // s
        setContentView(R.layout.activity_character_sheet2);
        context = this;
        db = FirebaseDatabase.getInstance("https://dndapp-b52b2-default-rtdb.firebaseio.com");
        mDatabase = db.getReference("Users");
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            username = extras.getString("player");
            if (extras.containsKey("index")) {
                sheetNum = extras.getInt("index");
                isTemplate = true;
            }
            else if (extras.containsKey("character")) {
                charName = extras.getString("character");
                isTemplate = false;

            }
        }
        saveButton = findViewById(R.id.buttonSaveChar);


        infoViews = new ArrayList<>();
        statViews = new ArrayList<>();
        resourceViews = new ArrayList<>();
        infoVals = new ArrayList<>();
        statVals = new ArrayList<>();



        infoRecycler = findViewById(R.id.recyclerView2);


    mDatabase.child(username).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            user = snapshot.getValue(Player.class);

            if(isTemplate) {
                sheetType = user.getSheets().get(sheetNum);
                System.out.println("Viewing a blank template!!!!!!!!");
            }
            else{
                charIndex = 0;
                for(DataSnapshot ds: snapshot.child("characters").getChildren()){
                    Character x = ds.getValue(Character.class);
                    System.out.println(x.getName());
                    System.out.println("charname: " + charName);
                    if(x.getName() == null){
                        continue;
                    }
                    if(x.getName().equals(charName)){
                        sheetType = x.getTemplate();
                        infoVals = x.getInfo();
                        for(ArrayList<String> group : x.getStats()){
                            statVals.add(group);
                        }
                        break;
                    }
                    charIndex++;
                }


            }
            infoFields = sheetType.getInfo();
            stats = sheetType.getStats();
            statCats = sheetType.getStatCats();
            infoTable = findViewById(R.id.infoTable);
            TableRow row = findViewById(R.id.firstRow);
            TableRow secondrow = new TableRow(context);
            infoTable.addView(secondrow);
            infoTable.setStretchAllColumns(true);
            //TableLayout.LayoutParams params = new TableLayout.LayoutParams();
            int n = 0;
            int x = 0;
            if(isTemplate){
                setValues();
            }
            for(String field : infoFields){
                TextView view = new TextView(context);
                view.setText(field);
                row.addView(view);
                TextView eview = new EditText(context);
                eview.setText(infoVals.get(x));
                secondrow.addView(eview);
                n++;
                x++;
                if(n >= 4){
                    row = new TableRow(context);
                    infoTable.addView(row);
                    secondrow = new TableRow(context);
                    infoTable.addView(secondrow);
                    n = 0;
                }
            }
           // setValues();

            statRec = findViewById(R.id.statRecyclerConstraint);
            statRec.setAdapter(new SheetCatAdapter(context, statCats, stats, statVals));
            statRec.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

                n++;
            }


        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
    saveButton.setOnClickListener(view -> {
        updateValues();
        if(character == null){
            character = new Character(sheetType);

        }
        character.setInfo(infoVals);
        character.setGroups(statVals);
        if(isTemplate) {
            System.out.println("Creating new character!");
            user.getCharacters().add(character);
        }
        else{
            System.out.println("Saving existing character!");
            user.getCharacters().set(charIndex, character);
        }
        mDatabase.child(username).child("characters").setValue(user.getCharacters());
    });
    }
    public void setValues(){


        int n = 0;
        while (n < infoFields.size()){
            infoVals.add("");
            n++;
        }
        n = 0;
        int r = 0;
        while(n < statCats.size()){
            ArrayList<String> list = new ArrayList<>();
            while(r < stats.get(statCats.get(n)).size()){
                list.add("");
                r++;
            }
            statVals.add(list);
            n++;
        }
    }
    public void updateValues(){
        int n = 0;
        int r = 1;
        infoVals = new ArrayList<>();
        statVals = new ArrayList<>();
        while(r < infoTable.getChildCount() - 2){
            TableRow row = (TableRow)infoTable.getChildAt(r);
            EditText info = (EditText) row.getChildAt(n);
            infoVals.add(info.getText().toString());
            n++;
            if(n >= 4){
                r = r + 2;
                n = 0;
            }
        }
        n = 0;
        r = 0;
        while(n < statRec.getAdapter().getItemCount()){
            ArrayList<String> tempList = new ArrayList<>();
            SheetCatAdapter adapter = (SheetCatAdapter) statRec.getAdapter();
            if(n >= adapter.getSize()){
                break;
            }
            RecyclerView recyclerView = adapter.getItem(n);
            while(r < recyclerView.getAdapter().getItemCount()){
                SheetSkillAdapter sadapter = (SheetSkillAdapter)recyclerView.getAdapter();
                if(r >= sadapter.getSize()){
                    break;
                }
               EditText editText = sadapter.getItem(r);
               tempList.add(editText.getText().toString());
                r++;
            }
            statVals.add(tempList);
            r = 0;
            n++;
        }
    }


}