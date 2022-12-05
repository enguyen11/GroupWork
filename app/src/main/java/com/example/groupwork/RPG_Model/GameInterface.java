package com.example.groupwork.RPG_Model;

public interface GameInterface {


    public Player getDM();

    public void setGM(Player GM);

    public Player[] getPlayers();

    public void addPlayer(Player newPlayer);

    public void removePlayer(Player booted);

    public Map[] getMaps();

    public void addMap(Map newMap);

    public void removeMap(Map removed);







}
