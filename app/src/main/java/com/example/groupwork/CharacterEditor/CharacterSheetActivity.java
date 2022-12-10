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
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.groupwork.R;
import com.example.groupwork.RPG_Model.Player;
import com.example.groupwork.RPG_Model.Resource;
import com.example.groupwork.RPG_Model.SheetType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


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
        }

        infoViews = new ArrayList<>();
        statViews = new ArrayList<>();
        resourceViews = new ArrayList<>();


        //params.addRule(ConstraintLayout.CENTER_IN_PARENT);
        infoRecycler = findViewById(R.id.recyclerView2);



        int n = 0;

       //setContentView(layout, params);
    mDatabase.child(username).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (user == null) {
                user = snapshot.getValue(Player.class);
                System.out.println("Username in charactersheetactivity " + user.getName());

            }
            else {
                System.out.println("Username from charactersheetactivity: " + user.getName());
            }
            sheetType = user.getSheets().get(0);
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
            for(String field : infoFields){
                TextView view = new TextView(context);
                view.setText(field);
                row.addView(view);
                TextView eview = new EditText(context);
                secondrow.addView(eview);
            }

            statRec = findViewById(R.id.statRecyclerConstraint);
            statRec.setAdapter(new SheetCatAdapter(context, statCats, stats));
            statRec.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));



                n++;
            }


        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
    }

}