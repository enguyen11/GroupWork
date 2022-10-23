package com.example.groupwork.model;


import com.google.gson.annotations.SerializedName;

/**
 * When using the api/equipment/{index} endpoint with an index
 */
public class Equipment {

    private String name;
    private String index;
    private String url;
    private String description;
    //an equipment category is displayed in an equipment object the same way as objects in
    // the list from an endpoint
    private Dnd5eItem equipment_category;

    //We can have different name, just use specify which field is to be associated as below

    @SerializedName("body")
    private String text;

    public Equipment(String index, String name, String url,
                     String description, Dnd5eItem equipment_category) {
        this.index = index;
        this.name = name;
        this.url = url;
        this.description = description;
        this.equipment_category = equipment_category;
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


    public String getDescription() {
        return description;
    }

    public Dnd5eItem getEquipment_category() {
        return equipment_category;
    }

    public String getEquipmentCategoryName() {
        return equipment_category.getName();
    }

}
