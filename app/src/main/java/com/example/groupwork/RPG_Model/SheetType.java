package com.example.groupwork.RPG_Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class SheetType implements Parcelable {
    private String sheetName;
    private ArrayList<String> infoFields;
    private LinkedHashMap<String, ArrayList<String>> stats;
    private LinkedHashMap<String, Resource> resources;

    public SheetType(){
        stats = new LinkedHashMap<>(100);
        resources = new LinkedHashMap<>(100);

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
    public LinkedHashMap<String, ArrayList<String>> getStats(){
        return this.stats;
    }
    public void addStat(String statType, ArrayList<String> statNames){
        this.stats.put(statType, statNames);
    }
    public void setStats(LinkedHashMap<String, ArrayList<String>> stats){
        this.stats = stats;
    }
    public void addResource(String statType, Resource statNames){
        this.resources.put(statType, statNames);
    }
    public void setResources(LinkedHashMap<String, Resource> stats){
        this.resources = stats;
    }
    public LinkedHashMap<String, Resource> getResources(){
        return this.resources;
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
