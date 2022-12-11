package com.example.groupwork.board;

import android.util.Pair;
import java.util.Hashtable;

public class BattleMapManager {
    private BattleMapActivity activity;
    private Hashtable<Pair<Integer, Integer>, BoardPiece> pieces;
    private Hashtable<Pair<Integer, Integer>, Tile>  tileHashtable;
    private boolean isReady;

    BattleMapManager(BattleMapActivity activity){
        this.activity = activity;
        this.tileHashtable = null;
        this.pieces = null;
        this.isReady = false;
    }

    public void importData(Hashtable<Pair<Integer, Integer>, Tile>  tileHashtable, Hashtable<Pair<Integer, Integer>, BoardPiece> pieces){
        this.tileHashtable = tileHashtable;
        this.pieces = pieces;
        this.isReady = true;
    }

    public synchronized void build() {
        if (!(isReady)) return;
        for (BoardPiece piece: this.pieces.values()) {
            if (this.tileHashtable.contains(piece.getCoordinates())){
                Tile current = this.tileHashtable.get(piece.getCoordinates());
                if (current != null & !(current.isOccupied())){
                    current.occupy(piece);
                }
            }
        }
    }





}
