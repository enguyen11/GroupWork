package com.example.groupwork.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * When making a call to an endpoint without an index specified (or query params for spells and monsters)
 * the response is a JSON Object with the count of the items in the list and a JSON Array or "results"
 *
 * the "results" is an array of Dnd5eItems
 */
public class Dnd5eItemList {
    @SerializedName("results")
    private ArrayList<Dnd5eItem> results;

    public Dnd5eItemList(ArrayList<Dnd5eItem> results) {
        this.results = results;
    }

    // declare getters
    public ArrayList<Dnd5eItem> getItems() {
        return results;
    }


}
