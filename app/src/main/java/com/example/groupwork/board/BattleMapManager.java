package com.example.groupwork.board;

import android.util.Log;
import android.util.Pair;

import java.util.Hashtable;

public class BattleMapManager {
    private BattleMapActivity activity;
    private Hashtable<Pair<Integer, Integer>, BoardPiece> pieces;
    private Hashtable<Pair<Integer, Integer>, Tile> tileHashtable;
    private boolean isReady;
    private Tile selection;

    BattleMapManager(BattleMapActivity activity) {
        this.activity = activity;
        this.tileHashtable = null;
        this.pieces = null;
        this.isReady = false;
    }

    public void importData(Hashtable<Pair<Integer, Integer>, Tile> tileHashtable, Hashtable<Pair<Integer, Integer>, BoardPiece> pieces) {
        this.tileHashtable = tileHashtable;
        this.pieces = pieces;
        this.isReady = true;
    }

    public void build() {
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
    public void trySelectTile(int x, int y) throws NullPointerException {
        Log.d("TILE", String.format("attempting to select tile %d, %d", x, y));
        if (!(isReady)) return;

        Tile tile = tileHashtable.get(new Pair<>(x, y));
        Log.d("TILE", String.format("Is occupied? %b", tile.isOccupied()));
        if (tile == null) throw new NullPointerException("hashtable item not found for x and y");

        // first case
        if (this.selection == null & tile.isOccupied()) {
            this.selection = tile;
            return;
        } else if (this.selection == null) {
            return;
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
            selection = null;

        } else {
            selection = null;
        }
    }


}