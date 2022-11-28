package com.example.groupwork.DndApiActivity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EquipmentCategoryList {
    @SerializedName("equipment")
    private List<Dnd5eItem> equipment;

    public EquipmentCategoryList(List<Dnd5eItem> equipment) {
        this.equipment = equipment;
    }
    // declare getters
    public List<Dnd5eItem> getItems() {
        return equipment;
    }

}
