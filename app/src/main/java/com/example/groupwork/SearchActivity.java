package com.example.groupwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnd_api_menu);
    }

    // Choosing the option to search for equipment
    //todo actually implement this
    public void selectEquipment(android.view.View view){
        // sends request to web service: /api/equipment/<search-term>
        /*NOTE: search index is always lowercase
          NOTE: Game objects with a space in their name (e.g. Leather Armor)
          are written with a hyphen (e.g. studded-leather)

         */

        // makes an additional drop-down list available to further specify an item category
        //e.g. weapons, armor, tools, land vehicles, water vehicles, etc
        // if no choice of category, stick with /api/equipment/<search-term>
        // if category is chosen, request /api/equipment-categories/<category>
        //this will automatically display a list of items in that category, clicking on one
        //would open its description just as if you had searched for it
    }
}