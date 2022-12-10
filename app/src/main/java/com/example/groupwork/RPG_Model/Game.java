package com.example.groupwork.RPG_Model;

import java.util.ArrayList;

public class Game {

    private String name;
    private String gameMaster;
    private String system;
    private int numPlayers;
    private ArrayList<String> party;
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
        this.party = new ArrayList<>();
        party.add(gameMaster);
        this.partyCharacters = new ArrayList<>();
    }

    public void addPlayer(String player) {
        if(this.party == null) {
            this.party = new ArrayList<>();
        }
        if (this.party.size() < numPlayers) {
            this.party.add(player);
        }
    }

    public void addCharacter(String character) {
        if (party.size() == partyCharacters.size() - 1) {
            partyCharacters.add(character);
        }
    }

    public String getName() {return this.name;}
    public String getGameMaster() {return this.gameMaster;}
    public String getSystem() {return this.system;}
    public int getNumPlayers() {return this.numPlayers;}
    public void setDescr(String s) {this.descr = s;}
    public String getDescr() {return this.descr;}
    public ArrayList<String> getParty() {return this.party;}

    public ArrayList<String> partyCharacters() {
        return this.partyCharacters;
    }
}
