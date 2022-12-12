package com.example.groupwork.board;

import android.util.Log;
import android.util.Pair;

import com.google.firebase.database.FirebaseDatabase;

import java.util.Hashtable;

public class BattleMapManager {
    private final BattleMapActivity activity;
    private Hashtable<Pair<Integer, Integer>, BoardPiece> pieces;
    private Hashtable<Pair<Integer, Integer>, Tile> tileHashtable;
    private FirebaseDatabase db;
    private boolean isReady;
    private Tile selection;
    private Tile lastTouched;

    BattleMapManager(BattleMapActivity activity) {
        this.activity = activity;
        this.tileHashtable = null;
        this.pieces = null;
        this.isReady = false;
    }

    public void importData(Hashtable<Pair<Integer, Integer>, Tile> tileHashtable, Hashtable<Pair<Integer, Integer>, BoardPiece> pieces) {
        if (tileHashtable == null | pieces == null) return;
        this.tileHashtable = tileHashtable;
        this.pieces = pieces;
        this.isReady = true;
    }

    // this function puts pieces in their place
    public void putPieces() {
        if (!(isReady)) return;
        for (BoardPiece piece : this.pieces.values()) {
            Log.d("TILE", "FOR LOOP");

            Tile current = this.tileHashtable.get(piece.getCoordinates());
            if (current != null) {
                Log.d("TILE", String.format("PIECE IS OCCUPIED! attempting to populate %d:%d", piece.getCoordinates().first, piece.getCoordinates().second));
                current.occupy(piece);
                activity.updateTile(current);
            }

        }
    }

    // genral pseudo code for the listener
    // if select is null and the tiles is unoccupied, do nothing
    // if select is null and the tile is occupied, we select that tile!
    // if we have something selected and we select an unoccupied tile, we free tile0 and occupy tile1
    // if we have something selected and we select an occupied tile, we FREE our selection, maybe mark piece as deselected.
    public boolean trySelectTile(int x, int y) throws NullPointerException {

        if (!(isReady)) return false;
        Pair<Integer,Integer> pair = new Pair<>(x, y);
        Tile tile = tileHashtable.get(pair);

        if (tile == null) throw new NullPointerException("hashtable item not found for x and y");

        // first case
        if (this.selection == null & tile.isOccupied()) {
            this.selection = tile;
            return true;
        } else if (this.selection == null) {
            return false;
        }

        // assume selection is not null
        if (!(tile.isOccupied())) {

            // free tile
            BoardPiece piece = selection.free();
            // occupy tile
            tile.occupy(piece);
            // update display
            activity.updateTile(tile);
            activity.updateTile(selection);
            //update hashmap
            updatePieceTable(piece, tile.getCoor()[0], tile.getCoor()[1]);
            selection = null;
            return true;
        }
        if (pair == new Pair<>(selection.getCoor()[0], selection.getCoor()[1])) return false;

        selection = null;
        return false;
    }


    public boolean addPiece(BoardPiece piece) {
        if (!(isReady)) return false;

        int x, y;
        int[] coor;
        if (lastTouched == null){
            Log.d("SELECTION", "addPiece: Selection is null");
            coor = findFreeSpot();
        } else if (!(lastTouched.isOccupied())){
            coor = lastTouched.getCoor();
        } else {
            Log.d("SELECTION", "addPiece: last touched is occupied");
            coor = findFreeSpot();
        }

        // final piece positions
        x = coor[0];
        y = coor[1];

        // perform add action
        Pair<Integer, Integer> key = new Pair<>(x, y);
        Tile tile = tileHashtable.get(key);
        if (tile == null | tile.isOccupied()) return false;
        updatePieceTable(piece, x, y);
        tile.occupy(piece);
        activity.updateTile(tile);
        return true;
    }

    public int[] findFreeSpot() {
        int[] coor;
        for (Tile tile : tileHashtable.values()
        ) {
            if (tile.isOccupied() == false) {
                return tile.getCoor();
            }
        }
        return coor = new int[]{-1, -1};
    }


    public boolean deletePiece() {
        if (!(isReady)) return false;
        if (selection == null) return false;
        Log.d("TILE", "Selection is not null!");
        BoardPiece myPiece = pieces.get(new Pair<>(selection.getCoor()[0], selection.getCoor()[1]));
        if (myPiece == null) return false;
        Log.d("TILE", "Piece is not null!");
        pieces.remove(new Pair<>(selection.getCoor()[0], selection.getCoor()[1]));
        selection.free();
        selection.updateDisplay();
        selection = null;
        return true;
    }

    private void updatePieceTable(BoardPiece piece, int x, int y){
        pieces.remove(piece.getCoordinates());
        piece.setNewCoordinates(x, y);
        pieces.put(new Pair<>(x, y), piece);
    }

    public Tile getLastTouched(){
        return lastTouched;
    }

    public void setLastTouched(Tile lastTouched){
        this.lastTouched = lastTouched;
    }

}