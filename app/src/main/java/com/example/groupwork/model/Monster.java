package com.example.groupwork.model;

import com.google.gson.annotations.SerializedName;

public class Monster {


    private String name;
    private String index;
    private String url;
    private String description;

    public Monster(String name, String index, String url,
                     String description) {
        this.index = index;
        this.name = name;
        this.url = url;
        this.description = description;
    }

    // declare getters
    public String getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }


    public String getURL() {
        return url;
    }


    public String getDescription() {
        return description;
    }


}
