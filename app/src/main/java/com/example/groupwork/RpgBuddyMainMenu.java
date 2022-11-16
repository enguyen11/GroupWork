package com.example.groupwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomappbar.BottomAppBar;
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