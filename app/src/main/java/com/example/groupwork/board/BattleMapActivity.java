package com.example.groupwork.board;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import androidx.gridlayout.widget.GridLayout;

import android.widget.ImageView;

import com.example.groupwork.R;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class BattleMapActivity extends AppCompatActivity {

    private GridLayout grid;
    private int width, height;
    private Handler handler;
    private BattleMapManager mapManager;

    // our pieces
    private Hashtable<Pair<Integer, Integer>, BoardPiece> pieces;
    // tiles
    private Hashtable<Pair<Integer, Integer>, Tile> battleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_map);
        //TODO ERASE
        width = 12;
        height = 30;
        //grid
        grid = (GridLayout) findViewById(R.id.BattleMap);
        grid.setColumnCount(width);
        grid.setRowCount(height);
        grid.setAlignmentMode(GridLayout.ALIGN_MARGINS);

        //variables
        handler = new Handler(Looper.getMainLooper());
        battleMap = new Hashtable<>();
        pieces = new Hashtable<>();
        mapManager = new BattleMapManager(this);

        // this thread runs the setup
        Thread setup = new Thread(new Runnable() {
            public void run() {
                boolean black = false;
                Hashtable<Pair<Integer, Integer>, Tile> map = new Hashtable<>();
                Pair<Integer, Integer> key;
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        View battle_map_tile = getLayoutInflater().inflate(R.layout.battle_map_tile, null);
                        ImageView image = battle_map_tile.findViewById(R.id.tile);
                        ImageView characterImage = battle_map_tile.findViewById(R.id.character);
                        characterImage.setVisibility(View.INVISIBLE);
                        if (black) image.setImageResource(R.drawable.square_gray);

                        Tile tile = new Tile(j, i, battle_map_tile);
                        black = !black;
                        key = new Pair<>(j, i);
                        map.put(key, tile);
                    }
                    black = !black;
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        createGrid(map);
                    }
                });
            }
        });
        setup.start();

        //Create Manager

    }

    public Hashtable<Pair<Integer, Integer>, Tile> getMap() {
        return battleMap;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Handler getHandler() {
        return handler;
    }


    // this creates a grid in UI, main thread ONLY
    public void createGrid(Hashtable<Pair<Integer, Integer>, Tile> map) {
        this.battleMap = map;
        Pair<Integer, Integer> key;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                key = new Pair<>(j, i);
                View currentView = battleMap.get(key).getView();
                setTileClickListener(currentView);
                if (currentView != null) grid.addView(currentView);
            }
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                mapManager.importData(map, pieces);
                mapManager.build();
            }
        }).start();
    }

    // this cade sets the interactivity fof a tile view ImageView
    private void setTileClickListener(View currentView){
        currentView.setOnTouchListener(new View.OnTouchListener() {
                                           @Override
                                           public boolean onTouch(View view, MotionEvent motionEvent) {
                                               if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                                                   ImageView image = view.findViewById(R.id.tile);
                                                   image.setColorFilter(Color.YELLOW);
                                                   return true;
                                               }
                                               ImageView image = view.findViewById(R.id.tile);
                                               image.clearColorFilter();
                                               return false;
                                           }
                                       }
        );
    }

    public void updateTile(Tile tile){
        tile.updateDisplay();
    }
}