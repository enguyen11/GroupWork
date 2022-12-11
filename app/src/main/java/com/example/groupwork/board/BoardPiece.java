package com.example.groupwork.board;

import android.util.Pair;

public interface BoardPiece {
    Pair<Integer, Integer> getCoordinates();

    String getPieceId();

    boolean isSelected();

    int getImageSourceId();

    String getNameId();
    void setNewCoordinates(int x, int y);
}
