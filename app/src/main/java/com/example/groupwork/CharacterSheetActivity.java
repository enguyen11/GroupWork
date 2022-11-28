package com.example.groupwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            user = extras.getParcelable("player");
            System.out.println("user sheets: " + user.getSheets());
        }
        infoViews = new ArrayList<>();
        statViews = new ArrayList<>();
        resourceViews = new ArrayList<>();
        sheetType = user.getSheets().get(0);
        sheetName = sheetType.getName();
        System.out.println("sheet name: " + sheetName);
        infoFields = sheetType.getInfo();
        stats = sheetType.getStats();
        System.out.println("info: " + infoFields);
        resources = sheetType.getResources();
        RelativeLayout layout = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        //params.addRule(RelativeLayout.CENTER_IN_PARENT);

        int n = 0;
        while (n < infoFields.size()){
            EditText edit = new EditText(this);
            edit.setId(n);
            edit.setHint(infoFields.get(n));
            edit.setLayoutParams(params);
            if(n > 0){
                RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                p.addRule(RelativeLayout.ALIGN_RIGHT, n-1);
                edit.setLayoutParams(p);
            }
            layout.addView(edit);
            infoViews.add(edit);
            n++;
        }

        n = 0;
        while (n < stats.size()){
            EditText edit = new EditText(this);
            ArrayList<String> names = new ArrayList<>(stats.keySet());
            edit.setHint(names.get(n));
            statViews.add(edit);
            n++;
        }/*
        n = 0;
        while (n < resources.size()){
            EditText edit = new EditText(this);
            ArrayList<String> names = new ArrayList<>(resources.keySet());
            edit.setHint(names.get(n));
            resourceViews.add(edit);
            n++;
        }*/
        setContentView(layout, params);

    }
}