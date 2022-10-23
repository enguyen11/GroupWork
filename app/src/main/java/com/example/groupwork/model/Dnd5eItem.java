package com.example.groupwork.model;

import com.google.gson.annotations.SerializedName;

/**
 * These items make up the objects in the results list of the response from calling an endpoint
 */
public class Dnd5eItem {

    private String name;
    private String index;
    private String url;

    public Dnd5eItem(String index,String name, String url) {
        this.index = index;
        this.name = name;
        this.url = url;
    }

    // declare
    public String getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }


    public String getURL() {
        return url;
    }


}
