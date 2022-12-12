package com.example.groupwork.Menus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;

import com.example.groupwork.CharacterEditor.RpgBuddyCharacterEditor;
import com.example.groupwork.DiceRoller.rpgBuddyDiceRoller;
import com.example.groupwork.GameCreation.GMGameCreation;
import com.example.groupwork.GameCreation.PlayerJoinGame;
import com.example.groupwork.GameCreation.SelectPlayerTypeDialog;
import com.example.groupwork.R;
import com.example.groupwork.RPG_Model.Game;
import com.example.groupwork.RPG_Model.Player;
import com.example.groupwork.board.BattleMapActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LoadedGameActivity extends AppCompatActivity {

    private ArrayList<Game> myGames;
    private Button btnNewGame;

    private Player user;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabase2;
    private String username;
    private String campaign;
    private Button mapBtn;

    private NavArgument nameArg, campaignArg;

    public static String TAG = "LoadedGameActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaded_game_main_menu);

        changeFragment(new LoadedGameMenuFragment());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("user");
            campaign = extras.getString("campaignName");
        }

        db = FirebaseDatabase.getInstance("https://dndapp-b52b2-default-rtdb.firebaseio.com");
        mDatabase = db.getReference("Users");
        user = new Player(username);
        if(mDatabase.child(username) == null) {
            mDatabase.child(username).setValue(user);
        }
        mDatabase2 = db.getReference("Games");
        if(mDatabase2 == null) {
            mDatabase2.setValue("default");
        }

        nameArg = new NavArgument.Builder().setDefaultValue(username).build();
        campaignArg= new NavArgument.Builder().setDefaultValue(campaign).build();
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView2);
        NavInflater navInflater = navController.getNavInflater();
        NavGraph navGraph = navInflater.inflate(R.navigation.loaded_game_nav_bar);
        navGraph.addArgument("user", nameArg);
        navGraph.addArgument("campaignName", campaignArg);
        navController.setGraph(navGraph);

        Bundle args = new Bundle();
        args.putString("user", username);
        args.putString("campaignName", campaign);


        Fragment defaultFrag = new LoadedGameMenuFragment();
        defaultFrag.setArguments(args);
        changeFragment(defaultFrag);

        // FOLLOWING CODE MANAGES THE DIFFERENT FRAGMENTS IN THE MAIN SCREENS
        BottomNavigationView bottomNav = findViewById(R.id.loadedGame_bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            Fragment fragment;
            if (R.id.loadedGameMainMenu == itemId) {
                fragment = new LoadedGameMenuFragment();
                fragment.setArguments(args);
                changeFragment(fragment);
            } else if (R.id.currentCharacterSheet == itemId) {
                //character sheet
//                fragment = new RpgBuddyCharacterEditor();
//                fragment.setArguments(args);
//                changeFragment(fragment);
            } else if (R.id.rpgBuddyDiceRoller == itemId) {
                fragment = new rpgBuddyDiceRoller();
                changeFragment(fragment);
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
        transaction.replace(R.id.fragmentContainerView2, fragment);
        transaction.commit();

    }
};