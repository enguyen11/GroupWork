package com.example.groupwork.RPG_Model;

import java.util.ArrayList;

public class Game {

    private String name;
    private String gameMaster;
    private String system;
    private int numPlayers;
    private ArrayList<String> party;
    private String descr;


    public Game(String name, String gameMaster, String system, int numPlayers){
        this.name = name;
        this.gameMaster = gameMaster;
        this.system = system;
        this.numPlayers = numPlayers;
        this.descr = "";
    }

    public void addPlayer(String player) {
        if (party.size() < numPlayers) {
            party.add(player);
        }
    }

    public String getName() {return this.name;}
    public String getGameMaster() {return this.gameMaster;}
    public String getSystem() {return this.system;}
    public int getNumPlayers() {return this.numPlayers;}
    public void setDescr(String s) {this.descr = s;}
    public String getDescr() {return this.descr;}

    public ArrayList<String> getParty() {
        return party;
    }
}
