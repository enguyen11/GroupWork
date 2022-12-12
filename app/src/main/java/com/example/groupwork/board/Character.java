package com.example.groupwork.board;

import android.util.Pair;

public class Character implements BoardPiece {
    private int x;
    private int y;
    private boolean isSelected;
    final private int imageSourceId;
    final private String name;

    Character(int x, int y, String name, int imageSourceID){
        this.x = x;
        this.y = y;
        this.imageSourceId = imageSourceID;
        this.name = name;
    }

    public void setNewCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public Pair<Integer, Integer> getCoordinates() {
        return new Pair<Integer, Integer>(x, y);
    }

    @Override
    public String getNameId() {
        return name;
    }


    @Override
    public int getImageSourceId() {
        return this.imageSourceId;
    }

}
