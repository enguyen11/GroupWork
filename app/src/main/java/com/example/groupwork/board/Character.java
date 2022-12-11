package com.example.groupwork.board;

public class Character implements BoardPiece {
    private int x;
    private int y;


    @Override
    public void moveTo(int x, int y) {

    }

    @Override
    public String getPieceId() {
        return null;
    }

    public int[] getCoor(){
        int[] coor = {this.x, this.y};
        return coor;
    }
}
