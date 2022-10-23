package com.example.groupwork.model;


import com.google.gson.annotations.SerializedName;

/**
 * When using the api/equipment/{index} endpoint with an index
 */
public class Equipment extends Dnd5eItem {


    private String description;
    //an equipment category is displayed in an equipment object the same way as objects in
    // the list from an endpoint
    private Dnd5eItem equipment_category;


    public Equipment(String index, String name, String url,
                     String description, Dnd5eItem equipment_category) {
        super(index,name,url);
        this.description = description;
        this.equipment_category = equipment_category;
    }


    public String getDescription() {
        return description;
    }


    public String getEquipmentCategoryName() {
        return equipment_category.getName();
    }

}
