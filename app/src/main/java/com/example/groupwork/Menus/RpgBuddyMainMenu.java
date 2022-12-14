package com.example.groupwork.Menus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.groupwork.CharacterEditor.RpgBuddyCharacterEditor;
import com.example.groupwork.GameCreation.GMGameCreation;
import com.example.groupwork.GameCreation.PlayerJoinGame;
import com.example.groupwork.GameCreation.SelectPlayerTypeDialog;
import com.example.groupwork.R;
import com.example.groupwork.DiceRoller.rpgBuddyDiceRoller;

import android.util.Log;
import android.widget.Button;

import com.example.groupwork.RPG_Model.Game;
import com.example.groupwork.RPG_Model.Player;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RpgBuddyMainMenu extends AppCompatActivity implements SelectPlayerTypeDialog.OnInputListener{

    private ArrayList<Game> myGames;
    private Button btnNewGame;
    private Button btnNewSheet;
    private Player user;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabase2;
    private String username;


    public static String TAG = "RpgBuddyMainMenu";

    private NavArgument nameArg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rpg_buddy_main_menu);



        db = FirebaseDatabase.getInstance("https://dndapp-b52b2-default-rtdb.firebaseio.com");
        mDatabase = db.getReference("Users");
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null){
            username = extras.getString("username");
        }
       // user = new Player("User");
        //mDatabase.child("User").setValue(user);
        user = new Player(username);
        if(mDatabase.child(username) == null) {
            mDatabase.child(username).setValue(user);
        }
        mDatabase2 = db.getReference("Games");
        if(mDatabase2 == null) {
            mDatabase2.setValue("default");
        }


        nameArg = new NavArgument.Builder().setDefaultValue(username).build();
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavInflater navInflater = navController.getNavInflater();
        NavGraph navGraph = navInflater.inflate(R.navigation.rpg_buddy_main_nav_bar);
        navGraph.addArgument("userID", nameArg);
        navController.setGraph(navGraph);

        Bundle args = new Bundle();
        args.putString("userID", user.getName());
        Fragment defaultFrag = new RpgBuddyGameMainMenu();
        defaultFrag.setArguments(args);
        changeFragment(defaultFrag);

        // FOLLOWING CODE MANAGES THE DIFFERENT FRAGMENTS IN THE MAIN SCREENS
        BottomNavigationView bottomNav = findViewById(R.id.RpgBuddyBottomNav);

        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            Fragment fragment;
            if (R.id.rpgBuddyGameMainMenu == itemId) {
                fragment = new RpgBuddyGameMainMenu();
                fragment.setArguments(args);
                changeFragment(fragment);
            } else if (R.id.rpgBuddyCharacterEditor == itemId) {
                fragment = new RpgBuddyCharacterEditor();
                fragment.setArguments(args);
                changeFragment(fragment);
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
        transaction.replace(R.id.fragmentContainerView, fragment);
        transaction.commit();
    }


    @Override
    public void sendInput(String selection) {
        Intent i = null;
        Context context = getApplicationContext();
        if(selection == "gm") {
            i = new Intent(context, GMGameCreation.class);
        } else if(selection == "player"){
            i = new Intent(context, PlayerJoinGame.class);
        } else {
            return;
        }
        i.putExtra("user", user.getName());
        startActivity(i);
    }
};