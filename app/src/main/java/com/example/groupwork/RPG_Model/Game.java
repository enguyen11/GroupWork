package com.example.groupwork.RPG_Model;

import java.util.ArrayList;

public class Game {

    private String name;
    private String gameMaster;
    private String system;
    private int numPlayers;
    private ArrayList<Player> party;


    public Game(String name, String gameMaster, String system, int numPlayers){
        this.name = name;
        this.gameMaster = gameMaster;
        this.system = system;
        this.numPlayers = numPlayers;
    }

    public void addPlayer(Player p) {
        if (party.size() < numPlayers) {
            party.add(p);
        }
    }
}
