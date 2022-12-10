package com.example.groupwork.RPG_Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;

public class SheetType implements Parcelable {
    private String sheetName;
    private ArrayList<String> infoFields;
    private HashMap<String, ArrayList<String>> stats;
    private ArrayList<String> statCats;
    private HashMap<String, Resource> resources;

    public SheetType(){
        stats = new HashMap<>(100);
        resources = new HashMap<>(100);

    }

    protected SheetType(Parcel in) {
        sheetName = in.readString();
        infoFields = in.createStringArrayList();
    }

    public static final Creator<SheetType> CREATOR = new Creator<SheetType>() {
        @Override
        public SheetType createFromParcel(Parcel in) {
            return new SheetType(in);
        }

        @Override
        public SheetType[] newArray(int size) {
            return new SheetType[size];
        }
    };

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
        this.stats = (HashMap<String, ArrayList<String>>)stats;
    }
    public void addResource(String statType, Resource statNames){
        this.resources.put(statType, statNames);
    }
    public void setResources(HashMap<String, Resource> stats){
        this.resources = stats;
    }
    public HashMap<String, Resource> getResources(){
        return this.resources;
    }
    public void setStatCats(ArrayList<String> list){
        this.statCats = list;
    }
    public ArrayList<String> getStatCats(){
        return this.statCats;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(sheetName);
        parcel.writeStringList(infoFields);
    }
}
