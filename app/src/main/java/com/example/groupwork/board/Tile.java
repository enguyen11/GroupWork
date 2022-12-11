package com.example.groupwork.board;

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

    public void setIsOccupied(boolean occupied){
        isOccupied = occupied;
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
        if (isOccupied & view != null){
            ImageView image = view.findViewById(R.id.character);
            image.setVisibility(View.VISIBLE);
            image.setImageResource(this.piece.getImageSourceId());
        } else if (!isOccupied){
            ImageView image = view.findViewById(R.id.character);
            image.setVisibility(View.INVISIBLE);
        }
    }
}
