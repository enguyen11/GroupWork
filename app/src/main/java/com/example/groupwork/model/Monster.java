package com.example.groupwork.model;

import com.google.gson.annotations.SerializedName;

public class Monster {


    private String monsterName;
    private String monsterIndex;
    private String monsterURL;
    private String monsterDescr;

    public Monster(String monsterName, String monsterIndex, String monsterURL,
                     String monsterDescr) {
        this.monsterName = monsterName;
        this.monsterIndex = monsterIndex;
        this.monsterURL = monsterURL;
        this.monsterDescr = monsterDescr;
    }

    // declare getters
    public String getMonsterIndex() {
        return monsterIndex;
    }

    public String getMonsterName() {
        return monsterName;
    }


    public String getMonsterURL() {
        return monsterURL;
    }


    public String getMonsterDescr() {
        return monsterDescr;
    }


}
