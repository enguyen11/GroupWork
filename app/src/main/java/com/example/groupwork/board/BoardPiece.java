package com.example.groupwork.board;

import android.util.Pair;

public interface BoardPiece {
    Pair<Integer, Integer> getCoordinates();

    String getNameId();

    int getImageSourceId();

    void setNewCoordinates(int x, int y);
}
