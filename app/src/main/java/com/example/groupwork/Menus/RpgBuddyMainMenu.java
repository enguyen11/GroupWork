package com.example.groupwork.Menus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.groupwork.R;
import com.example.groupwork.DiceRoller.rpgBuddyDiceRoller;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RpgBuddyMainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rpg_buddy_main_menu);

        changeFragment(new RpgBuddyGameMainMenu());

        // FOLLOWING CODE MANAGES THE DIFFERENT FRAGMENTS IN THE MAIN SCREENS
        BottomNavigationView bottomNav = findViewById(R.id.RpgBuddyBottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (R.id.rpgBuddyGameMainMenu == itemId) {
                changeFragment(new RpgBuddyGameMainMenu());
            } else if (R.id.rpgBuddyCharacterEditor == itemId) {
                changeFragment(new RpgBuddyGameMainMenu());
            } else if (R.id.rpgBuddyDiceRoller == itemId) {
                changeFragment(new rpgBuddyDiceRoller());
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