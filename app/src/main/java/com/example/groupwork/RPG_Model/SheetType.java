package com.example.groupwork.RPG_Model;

import java.util.ArrayList;

public class SheetType {
    private String sheetName;
    private ArrayList<String> infoFields;
    private ArrayList<String[]> statNames;
    private ArrayList<String[]> statValues;
    private ArrayList<String> resourceNames;
    private ArrayList<Object[]> resources;

    public SheetType(){

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
    public void addStats(String[] statGroup){
        this.statNames.add(statGroup);
    }
    public ArrayList<String[]> getStats(){
        return this.statNames;
    }
    public void setStats(ArrayList<String[]> stats){
        this.statNames = stats;
    }
    public void addValues(String[] values){
        this.statValues.add(values);
    }
    public ArrayList<String[]> getStatValues(){
        return this.statValues;
    }
    public void setStatValues(ArrayList<String[]> values){
        this.statValues = values;
    }
    public ArrayList<String> getResourceNames(){
        return this.resourceNames;
    }
    public void addResourceName(String name){
        this.resourceNames.add(name);
    }
    public void setResourceNames(ArrayList<String> names){
        this.resourceNames = names;
    }
    public ArrayList<Object[]> getResources(){
        return this.resources;
    }
    public void addResource(Object[] resource){
        this.resources.add(resource);
    }
    public void setResources(ArrayList<Object[]> resources){
        this.resources = resources;
    }




}
