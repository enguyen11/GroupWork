package com.example.groupwork.board;

import android.util.Pair;

import java.util.HashMap;

public class DataStorePieces {

    private static HashMap<Pair<Integer, Integer>, BoardPiece> pairBoardPieceHashMap = null;

    public static void setPairBoardPiecesHashMap(HashMap<Pair<Integer, Integer>, BoardPiece> map){
        pairBoardPieceHashMap = map;
    }

   public static HashMap<Pair<Integer, Integer>, BoardPiece> getPairBoardPieceHashMap(){
        return pairBoardPieceHashMap;
   }
}
