package com.example.groupwork.RPG_Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Character {
    private String name;
    private SheetType template;
    private ArrayList<String> info;
    private ArrayList<ArrayList<String>> stats;


    public Character(){
        template = new SheetType();
        info = new ArrayList<>();
        stats = new ArrayList<>();
    }

    public Character(SheetType temp){
        template = temp;
        info = new ArrayList<>();
        stats = new ArrayList<>();
    }
    public void updateInfo(int pos, String data){
        info.set(pos, data);
    }
    public void updateGroup(int pos, ArrayList<String> data){
        stats.set(pos, data);
    }
    public void updateStats(int group, int pos, String data){
        stats.get(group).set(pos, data);
    }

    public void setInfo(ArrayList<String> set){
        info = set;
        name = info.get(0);
    }
    public void setGroups(ArrayList<ArrayList<String>> groups){
        stats = groups;
    }
    public ArrayList<String> getInfo(){
        return info;
    }
    public ArrayList<ArrayList<String>> getStats(){
        return stats;
    }
    public SheetType getTemplate(){
        return template;
    }

    public String getName() {
        return name;
    }
}
