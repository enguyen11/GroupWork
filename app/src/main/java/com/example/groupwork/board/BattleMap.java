package com.example.groupwork.board;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;

import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.groupwork.DiceRoller.rpgBuddyDiceRoller;
import com.example.groupwork.R;
import com.example.groupwork.model.dice.DiceThrow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class BattleMap extends AppCompatActivity {

    private GridLayout grid;
    private int width, height;

    // our pieces
    private ArrayList<BoardPiece> pieces;
    // tiles
    private Hashtable<Integer,Tile> battleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_map);
        width = 12;
        height = 30;
        grid = (GridLayout) findViewById(R.id.BattleMap);
        grid.setColumnCount(width);
        grid.setRowCount(height);
        grid.setAlignmentMode(GridLayout.ALIGN_MARGINS);



        battleMap = new Hashtable<>();
        BattleMapTasks tasks = new BattleMapTasks(this);
        tasks.run();
    }

    public Hashtable<Integer,Tile> getMap(){
        return battleMap;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

class BattleMapTasks implements Runnable {
    private BattleMap map;

    BattleMapTasks(BattleMap mainClass){
        this.map = mainClass;
    }

    @Override
    public void run() {
        int width = map.getWidth();
        int height = map.getHeight();
        boolean black = false;
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                View battle_map_tile = map.getLayoutInflater().inflate(R.layout.battle_map_tile, null);
                Tile tile = new Tile(0, 0, battle_map_tile);
                ImageView image = battle_map_tile.findViewById(R.id.tile);
                image.setColorFilter(Color.WHITE);
                ImageView characterImage = battle_map_tile.findViewById(R.id.character);
                characterImage.setVisibility(View.INVISIBLE);
                if (black) image.setColorFilter(Color.GRAY);
                black = !black;
                map.getMap().put(i+j, tile);
            }
            black = !black;
        }
    }

}