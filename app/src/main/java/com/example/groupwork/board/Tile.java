package com.example.groupwork.board;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.groupwork.R;

public class Tile {
    final private int y;
    final private int x;
    private boolean isOccupied;
    private View view;
    private BoardPiece piece;

    public Tile(int x, int y, View view) {
        this.x = x;
        this.y = y;
        this.view = view;
        this.isOccupied = false;
    }

    public int[] getCoor(){
        int[] coor = {this.x, this.y};
        return coor;
    }

    public boolean isOccupied(){
        return isOccupied;
    }

    public boolean occupy(BoardPiece piece){
        if (isOccupied) return false;
        this.piece = piece;
        isOccupied = true;
        return isOccupied;
    }

    public BoardPiece free(){
        BoardPiece swap;
        swap = this.piece;
        this.piece = null;
        isOccupied = false;
        return swap;
    }

    public View getView() {
        return view;
    }

    // CAN ONLY BE RAN BY THE MAIN THREAD!
    public void updateDisplay(){
        Log.d("TILE", "updateDisplay: attempting to update the display ");
        if (view == null) return;
        Log.d("TILE", "updateDisplay: test passed view is not null  ");
        if (isOccupied){
            Log.d("TILE", "updateDisplay: test passed isOccupied  ");
            ImageView image = view.findViewById(R.id.character);
            image.setVisibility(View.VISIBLE);
            image.setImageResource(this.piece.getImageSourceId());
        } else if (!isOccupied){
            Log.d("TILE", "updateDisplay: test failed, not occupied ");
            ImageView image = view.findViewById(R.id.character);
            image.setVisibility(View.INVISIBLE);
        }
    }
}
