package com.example.groupwork.CharacterEditor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.groupwork.R;
import com.example.groupwork.RPG_Model.Player;
import com.example.groupwork.RPG_Model.Resource;
import com.example.groupwork.RPG_Model.SheetType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CharacterSheetActivity extends AppCompatActivity {
    private Player user;
    private SheetType sheetType;
    private String sheetName;
    private ArrayList<String> infoFields;
    private LinkedHashMap<String, ArrayList<String>> stats;
    private LinkedHashMap<String, Resource> resources;
    private ArrayList<EditText> infoViews;
    private ArrayList<EditText> statViews;
    private ArrayList<EditText> resourceViews;
    private ConstraintLayout layout;
    private RecyclerView infoRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            user = extras.getParcelable("player");
        }
        setContentView(R.layout.activity_character_sheet);
        infoViews = new ArrayList<>();
        statViews = new ArrayList<>();
        resourceViews = new ArrayList<>();
        sheetType = user.getSheets().get(0);
        sheetName = sheetType.getName();
        infoFields = sheetType.getInfo();
        stats = sheetType.getStats();
        System.out.println("Stats: " + stats);
        resources = sheetType.getResources();
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

        SheetAdapter adapter = new SheetAdapter(this, infoFields);
        infoRecycler.setAdapter(adapter);
        infoRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        adapter.notifyDataSetChanged();
        RecyclerView copy1 = findViewById(R.id.recyclerView3);
        RecyclerView copy2 = findViewById(R.id.recyclerView4);
        copy1.setAdapter(adapter);
        copy2.setAdapter(adapter);
        copy1.setLayoutManager(new GridLayoutManager(this, 3));
        copy2.setLayoutManager(new GridLayoutManager(this, 2));


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

    }
}