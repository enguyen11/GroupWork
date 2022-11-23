package com.example.groupwork.RPG_Model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class SheetType {
    private String sheetName;
    private ArrayList<String> infoFields;
    private HashMap<String, ArrayList<String>> stats;
    private HashMap<String, ArrayList<Resource>> resources;

    public SheetType(){
        stats = new HashMap<>(100);
        resources = new HashMap<>(100);

    }

    public void setName(String name){
        this.sheetName = name;
    }
    public String getName(){
        return this.sheetName;
    }
    public void addInfo(String field){
        this.infoFields.add(field);
    }
    public ArrayList<String> getInfo(){
        return this.infoFields;
    }
    public void removeInfo(String field){
        this.infoFields.remove(String.valueOf(field));
    }
    public void setInfo(ArrayList<String> info){
        this.infoFields = info;
    }
    public HashMap<String, ArrayList<String>> getStats(){
        return this.stats;
    }
    public void addStat(String statType, ArrayList<String> statNames){
        this.stats.put(statType, statNames);
    }
    public void setStats(HashMap<String, ArrayList<String>> stats){
        this.stats = stats;
    }
    public void addResource(String statType, ArrayList<Resource> statNames){
        this.resources.put(statType, statNames);
    }
    public void setResources(HashMap<String, ArrayList<Resource>> stats){
        this.resources = stats;
    }
    public HashMap<String, ArrayList<Resource>> getResources(){
        return this.resources;
    }





}
