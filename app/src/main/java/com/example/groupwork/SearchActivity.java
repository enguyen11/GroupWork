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
        // makes an additional drop-down list available to further specify an item category
        //e.g. weapons, armor, tools, land vehicles, water vehicles, etc
        // if no choice of category, user must enter a search term in the bar
        // if category is chosen, request from web service: /api/equipment-categories/<category>
        //this will automatically display a list of items in that category, clicking on one
        //would open its description just as if you had searched for it

        // sends request to web service: /api/equipment/<search-term>
        //<search-term> is whatever is entered into the search bar
        //should be an exact match
        /*NOTE: search index is always lowercase
          NOTE: Game objects with a space in their name (e.g. Leather Armor)
          are written with a hyphen (e.g. studded-leather)
          -either we handle this ourselves or instruct the user to do so

         */


    }


}