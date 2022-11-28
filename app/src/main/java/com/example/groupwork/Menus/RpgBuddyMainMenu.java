package com.example.groupwork.Menus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.groupwork.CharacterEditor.RpgBuddyCharacterEditor;
import com.example.groupwork.R;

import com.example.groupwork.RPG_Model.Game;
import com.example.groupwork.RPG_Model.Player;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class RpgBuddyMainMenu extends AppCompatActivity {

    private ArrayList<Game> myGames;
    private Button btnNewGame;
    private Button btnNewSheet;
    private Player user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rpg_buddy_main_menu);

        changeFragment(new RpgBuddyGameMainMenu());

        user = new Player();
        System.out.println("**********************user: " + user);


        // FOLLOWING CODE MANAGES THE DIFFERENT FRAGMENTS IN THE MAIN SCREENS
        BottomNavigationView bottomNav = findViewById(R.id.RpgBuddyBottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            Fragment fragment;
            if (R.id.rpgBuddyGameMainMenu == itemId) {
                fragment = new RpgBuddyGameMainMenu();
                changeFragment(fragment);
            } else if (R.id.rpgBuddyCharacterEditor == itemId) {
                fragment = new RpgBuddyCharacterEditor();
                Bundle args = new Bundle();
                args.putParcelable("player", user);
                fragment.setArguments(args);
                changeFragment(fragment);
            } else if (R.id.rpgBuddyDiceRoller == itemId) {

            }
            return true;
        });

    }


    /*
    Meant to be used to switch out fragments from the view
     */
    private void changeFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainerView, fragment);
        transaction.commit();

    }

};