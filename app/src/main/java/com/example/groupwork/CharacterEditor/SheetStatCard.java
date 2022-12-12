package com.example.groupwork.CharacterEditor;


/**
 * This card represents a stat card in the the alternative character sheet
 */
public class SheetStatCard {
    private String dataID; // data id
    private String hint; // description of the input to the user
    private int iconId; // icon id, needs to be resolved before creation
    private int intData; //if data point is number, get the number in double format
    private String stringData; // get the string data from the input
    private boolean isNumber; // is the data we want a number? if not use get string

    SheetStatCard(String dataID, String hint, int iconID, boolean isNumber){
        this.hint = hint;
        this.iconId = iconID;
        this.isNumber = isNumber;
        this.dataID = dataID;
        this.intData = 0;
        this.stringData = "";
    }

    public String getId(){
        return getId();
    }

    public int getIconId(){
        return iconId;
    }

    public String getHint(){
        return hint;
    }

    public int getIntData(){
        return intData;
    }

    public String getStringData(){
        return stringData;
    }

    public void setIntData(int data){
        intData = data;
    }

    public void setStringData(String data){
        stringData = data;
    }

    public boolean isNumber(){
        return isNumber;
    }

}
