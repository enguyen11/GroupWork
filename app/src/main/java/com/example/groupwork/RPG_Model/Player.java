package com.example.groupwork.RPG_Model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.groupwork.SheetBuilderActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Player implements Parcelable {

    private ArrayList<SheetType> sheets;
    private ArrayList<Player> friends;
    private ArrayList<Character> characters;
    private ArrayList<Game> games;
    private ArrayList<Map> maps;

    public Player(){
        sheets = new ArrayList<>();
        SheetType defaultSheet = makeDefault();
        this.sheets.add(defaultSheet);

    }

    private SheetType makeDefault(){
        SheetType defaultSheet = new SheetType();
        defaultSheet.setName("D&D 5e Sheet");
        ArrayList<String> info = new ArrayList<>();
        info.add("Name");
        info.add("Race");
        info.add("Class");
        info.add("Background");
        defaultSheet.setInfo(info);

        LinkedHashMap<String, ArrayList<String>> statList = new LinkedHashMap<>(100);
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

        ArrayList<String> init = new ArrayList<>(
                Arrays.asList("Initiative")
        );
        statList.put("Initiative", init);

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

        LinkedHashMap<String, Resource> resourceList = new LinkedHashMap<>(100);
        Resource money = new Resource("Currency", 5);
        money.setAttributes(new String[]{"cp", "sp", "ep", "gp", "pp"});
        resourceList.put("Currency", money);

        Resource attacks = new Resource("Attacks", 4);
        attacks.setAttributes(new String[]{"Name", "Attack Bonus", "Damage", "Type"});
        resourceList.put("Attacks", attacks);
        defaultSheet.setResources(resourceList);
        return defaultSheet;

    }



    protected Player(Parcel in) {
        friends = in.createTypedArrayList(Player.CREATOR);
        sheets = in.createTypedArrayList(SheetType.CREATOR);
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
        parcel.writeTypedList(sheets);
    }
}
