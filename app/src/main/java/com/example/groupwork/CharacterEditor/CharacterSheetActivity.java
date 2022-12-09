package com.example.groupwork.CharacterEditor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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

        //sheetType = user.getSheets().get(0);
        //sheetType = mDatabase.child(username).child("sheets").child("0").get
     //   sheetName = sheetType.getName();
     //   infoFields = sheetType.getInfo();
     //   stats = sheetType.getStats();
        System.out.println("Stats: " + stats);
     //   resources = sheetType.getResources();
       /* layout = new ConstraintLayout(this);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );*/
        //params.addRule(ConstraintLayout.CENTER_IN_PARENT);
        infoRecycler = findViewById(R.id.recyclerView2);



        int n = 0;
        /*
        while (n < infoFields.size()){
            EditText edit = new EditText(this);
            edit.setId(n);
            edit.setHint(infoFields.get(n));
            edit.setLayoutParams(params);
            if(n > 0){
                ConstraintLayout.LayoutParams p = new ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT);
                edit.setLayoutParams(p);
            }
            //layout.addView(edit);
            infoViews.add(edit);
            n++;
        }*/




        //ConstraintSet conSet = new ConstraintSet();
        //conSet.connect(infoRecycler.getId(),3, copy1.getId(), 4);
        //conSet.connect(copy1.getId(),4, copy2.getId(), 3);
        //conSet.constrainPercentHeight(infoRecycler.getId(), 33);
        //conSet.constrainPercentHeight(copy1.getId(), 33);
       // conSet.constrainPercentHeight(copy2.getId(), 33);
        //layout.setConstraintSet(conSet);
       // layout.addView(infoRecycler);
       //layout.addView(copy1);
        //layout.addView(copy2);





/*
        n = 0;
        while (n < stats.size()){
            EditText edit = new EditText(this);
            ArrayList<String> names = new ArrayList<>(stats.keySet());
            edit.setHint(names.get(n));
            statViews.add(edit);
            n++;
        }
        n = 0;
        while (n < resources.size()){
            EditText edit = new EditText(this);
            ArrayList<String> names = new ArrayList<>(resources.keySet());
            edit.setHint(names.get(n));
            resourceViews.add(edit);
            n++;
        }*/
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

            /*
             SheetAdapter adapter = new SheetAdapter(context, infoFields);
             infoRecycler.setAdapter(adapter);
             infoRecycler.setLayoutManager(new GridLayoutManager(context, 2));
             adapter.notifyDataSetChanged();
            RecyclerView copy1 = findViewById(R.id.recyclerView3);
            RecyclerView copy2 = findViewById(R.id.recyclerView4);
             copy1.setAdapter(adapter);
             copy2.setAdapter(adapter);
            copy1.setLayoutManager(new GridLayoutManager(context, 3));
              copy2.setLayoutManager(new GridLayoutManager(context, 2));*/

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



            statTable = findViewById(R.id.statTable);
            statRec = findViewById(R.id.statRecycler);
            //************************************************Try this idea *****************************
            statTable.setShrinkAllColumns(true);

            row = new TableRow(context);
            secondrow = new TableRow(context);
            statTable.addView(row);
            statTable.addView(secondrow);
            for (String key : stats.keySet()){
                System.out.println("Adding key: " + key);
                System.out.println("N: " + n);
                TextView view = new TextView(context);
                view.setText(key);
                if(n > 3){
                    System.out.println("Making new rows!");
                    row = new TableRow(context);
                    secondrow = new TableRow(context);
                    statTable.addView(row);
                    statTable.addView(secondrow);
                    n = -1;
                }
                ViewGroup.LayoutParams tableParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                row.setLayoutParams(tableParams);
                secondrow.setLayoutParams(tableParams);
                row.addView(view);
                RecyclerView rview = new RecyclerView(context);
                rview.setHasFixedSize(true);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                //params.height = 50;
               // rview.setLayoutParams(params);
                rview.setAdapter(new SheetAdapter(context, stats.get(key)));
                rview.setLayoutManager(new LinearLayoutManager(context));
               // view.setText(stats.get(key).toString());
               // EditText edit = new EditText(context);

                secondrow.addView(rview);
                n++;
            }
            statTable.addView(new TableRow(context));
            //statTable.addView(row);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
    }

}