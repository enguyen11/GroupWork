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

import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.groupwork.R;

import java.util.Hashtable;

public class BattleMapActivity extends AppCompatActivity {

    private GridLayout grid;
    private int width, height;
    private Handler handler;
    private BattleMapManager mapManager;
    private Button addHero;
    private Button addFoe;
    private Button delPiece;

    // our pieces
    private Hashtable<Pair<Integer, Integer>, BoardPiece> pieces;
    // tiles
    private Hashtable<Pair<Integer, Integer>, Tile> battleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_map);
        //TODO ERASE
        width = 14;
        height = 14;
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

        //TODO ERASE
        pieces.put(new Pair<>(8, 9), new Character(8, 9, "Cool guy", R.drawable.sticker_d20));
        pieces.put(new Pair<>(4, 4), new Character(4, 4, "Cool guy", R.drawable.sticker_d20));
        // this thread runs the setup
        Thread setup = new Thread(runSetup());
        setup.start();

        addFoe = findViewById(R.id.foe_btn);
        addFoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewPiece(new Character(0, 0, "Ragnar", R.drawable.round_shield));
            }
        });
        addHero = findViewById(R.id.hero_btn);
        addHero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewPiece(new Character(0, 0, "Ragnar", R.drawable.sticker_swords_dnd_sword30));
            }
        });
        delPiece = findViewById(R.id.del_btn);
        delPiece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapManager.deletePiece()) {
                    newToast("Piece deleted");
                } else {
                    newToast("Nothing to delete");
                }
            }
        });
    }


    private Runnable runSetup() {
        Runnable task = new Runnable() {
            @Override
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
        };
        return task;
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
                Tile currentTile = battleMap.get(key);
                View currentView = currentTile.getView();
                setTileClickListener(currentView, currentTile);
                if (currentView != null) grid.addView(currentView);
            }
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                mapManager.importData(map, pieces);
                mapManager.putPieces();
            }
        }).start();
    }

    // this cade sets the interactivity fof a tile view ImageView
    private void setTileClickListener(View currentView, Tile currentTile) {
        currentView.setOnTouchListener(new View.OnTouchListener() {
                                           @Override
                                           public boolean onTouch(View view, MotionEvent motionEvent) {
                                               if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                                                   ImageView image = view.findViewById(R.id.tile);
                                                   if (mapManager.trySelectTile(currentTile.getCoor()[0], currentTile.getCoor()[1])) {
                                                       image.setColorFilter(Color.BLUE);
                                                   } else {
                                                       image.setColorFilter(Color.YELLOW);
                                                   }
                                                   return true;
                                               }
                                               ImageView image = view.findViewById(R.id.tile);
                                               image.clearColorFilter();
                                               return false;
                                           }
                                       }
        );
    }

    public void updateTile(Tile tile) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tile.updateDisplay();
            }
        });
    }

    private boolean addNewPiece(BoardPiece boardPiece) {
        boolean res = mapManager.addPiece(boardPiece);
        String msg = res ? String.format("New piece at %d:%d", boardPiece.getCoordinates().first, boardPiece.getCoordinates().second) : "Failed to place piece";
        newToast(msg);
        return res;
    }

    private void newToast(String msg) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getBaseContext(), msg, duration);
        toast.show();
    }

}