package com.example.groupwork.RPG_Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Player implements Parcelable {

    private ArrayList<SheetType> sheets;
    private ArrayList<Player> friends;
    private ArrayList<Character> characters;
    private ArrayList<Game> games;
    private ArrayList<Map> maps;

    public Player(){
        sheets = new ArrayList<>();
        SheetType defaultSheet = new SheetType();
        defaultSheet.setName("D&D 5e Sheet");
        ArrayList<String> info = new ArrayList<>();
        info.add("Name");
        info.add("Race");
        info.add("Class");
        info.add("Background");
        defaultSheet.setInfo(info);

        HashMap<String, ArrayList<String>> statList = new HashMap<>(100);
        ArrayList<String> level = new ArrayList<>(
            Arrays.asList("Level"));
        statList.put("Level", level);

        ArrayList<String> hp = new ArrayList<>(
                Arrays.asList("Maximum Hit Points",
                        "Current Hit Points", "Temporary Hit Points", "Hit Dice")
        );
        statList.put("Hit Points", hp);

        ArrayList<String> ac = new ArrayList<>(
                Arrays.asList("Armor Class")
        );
        statList.put("Armor Class", ac);

        ArrayList<String> speed = new ArrayList<>(
                Arrays.asList("Walking Speed", "Swimming Speed",
                        "Climbing Speed", "Flying Speed")
        );
        statList.put("Speed", speed);

        ArrayList<String> abilities = new ArrayList<>(
                Arrays.asList("Strength", "Dexterity", "Constitution",
                        "Intelligence", "Wisdom", "Charisma")
        );
        statList.put("Ability Scores", abilities);

        ArrayList<String> modifiers = new ArrayList<>(
                Arrays.asList("Strength", "Dexterity", "Constitution",
                        "Intelligence", "Wisdom", "Charisma")
        );
        statList.put("Ability Modifiers", modifiers);

        ArrayList<String> skills = new ArrayList<>(
                Arrays.asList("Acrobatics", "Athletics", "Arcana",
                        "Deception", "History", "Intimidation",
                        "Investigation", "Medicine", "Nature",
                        "Perception", "Performance", "Persuasion",
                        "Sleight of Hand", "Stealth", "Survival")
        );
        statList.put("Skills", skills);
        ArrayList<String> prof = new ArrayList<>(
                Arrays.asList("Proficiency Bonus")
        );
        statList.put("Proficiency Bonus", prof);

        defaultSheet.setStats(statList);

        this.sheets.add(defaultSheet);


    }

    protected Player(Parcel in) {
        friends = in.createTypedArrayList(Player.CREATOR);
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public ArrayList<SheetType> getSheets(){
        return this.sheets;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(friends);
    }
}
