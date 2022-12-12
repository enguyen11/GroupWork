package com.example.groupwork.RPG_Model;

import android.util.Log;

import java.util.ArrayList;

public class Game {

    private String name;
    private String gameMaster;
    private String system;
    private int numPlayers;
    private ArrayList<String> party;
    private String descr;
    private ArrayList<String> partyCharacters;
    private String curUserCharacter;
    private String curUser;


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
//        party.add(gameMaster);
        this.partyCharacters = new ArrayList<>();
//        partyCharacters.add("gameMaster");

    }

    public Game(String name, String curUserCharacter, String curUser){
        this.name = name;
        this.curUserCharacter = curUserCharacter;
        this.curUser = curUser;
    }


    public boolean addPlayer(String player, String character) {
        boolean wasAdded;
        if(this.party == null) {
            this.party = new ArrayList<>();
            this.partyCharacters = new ArrayList<>();
        }
        if (this.party.size() < this.numPlayers) {
            this.party.add(player);
            partyCharacters.add(character);
            wasAdded = true;
        } else {
            wasAdded = false;
        }
        return wasAdded;
    }



    public String getName() {return this.name;}
    public String getGameMaster() {return this.gameMaster;}
    public String getSystem() {return this.system;}
    public int getNumPlayers() {return this.numPlayers;}
    public void setDescr(String s) {this.descr = s;}
    public String getDescr() {return this.descr;}
    public ArrayList<String> getParty() {return this.party;}
    public String getCurUserCharacter() {return this.curUserCharacter;}
    public String getCurUser() {return this.curUser;}
    public ArrayList<String> partyCharacters() {
        return this.partyCharacters;
    }
}
