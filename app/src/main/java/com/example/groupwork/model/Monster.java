package com.example.groupwork.model;

import com.google.gson.annotations.SerializedName;

public class Monster extends Dnd5eItem {


    private String description;

    public Monster(String index,String name,  String url,
                     String description) {
        super(index, name,url);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}
