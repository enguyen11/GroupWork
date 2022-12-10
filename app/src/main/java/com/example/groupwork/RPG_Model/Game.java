package com.example.groupwork.RPG_Model;

import java.util.ArrayList;

public class Game {

    private String name;
    private String gameMaster;
    private String system;
    private int numPlayers;
    private ArrayList<String> partyPlayers;
    private String descr;
    private ArrayList<String> partyCharacters;


    public Game(){
        System.out.println("**************Making Game*********************");
    }
    public Game(String name, String gameMaster, String system, int numPlayers){
        this.name = name;
        this.gameMaster = gameMaster;
        this.system = system;
        this.numPlayers = numPlayers;
        this.descr = "";
        this.partyPlayers = new ArrayList<>();
        partyPlayers.add(gameMaster);
        this.partyCharacters = new ArrayList<>();
    }

    public void addPlayer(String player) {
        if(this.partyPlayers == null) {
            this.partyPlayers = new ArrayList<>();
        }
        if (this.partyPlayers.size() < numPlayers) {
            this.partyPlayers.add(player);
        }
    }

    public void addCharacter(String character) {
        if (partyPlayers.size() == partyCharacters.size() - 1) {
            partyCharacters.add(character);
        }
    }

    public String getName() {return this.name;}
    public String getGameMaster() {return this.gameMaster;}
    public String getSystem() {return this.system;}
    public int getNumPlayers() {return this.numPlayers;}
    public void setDescr(String s) {this.descr = s;}
    public String getDescr() {return this.descr;}
    public ArrayList<String> getPartyPlayers() {return this.partyPlayers;}

    public ArrayList<String> getParty() {
        return partyPlayers;
    }
}
