package com.example.groupwork.RPG_Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

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
        ArrayList<String[]> statTypes = new ArrayList<>();
        statTypes.add(new String[]{"Level"});
        statTypes.add(new String[]{"Hit Points"});
        statTypes.add(new String[]{"Armor Class"});
        statTypes.add(new String[]{"Walking Speed", "Swimming Speed", "Flying Speed"});
        String[] abilityScores = new String[6];
        abilityScores[0] = "Strength";
        abilityScores[1] = "Dexterity";
        abilityScores[2] = "Constitution";
        abilityScores[3] = "Intelligence";
        abilityScores[4] = "Wisdom";
        abilityScores[5] = "Charisma";
        statTypes.add(abilityScores);
        String[] modifiers = new String[6];
        modifiers[0] = "Strength";
        modifiers[1] = "Dexterity";
        modifiers[2] = "Constitution";
        modifiers[3] = "Intelligence";
        modifiers[4] = "Wisdom";
        modifiers[5] = "Charisma";
        statTypes.add(modifiers);
        String[] skills = new String[16];
        skills[0] = "Acrobatics";
        skills[1] = "Animal Handling";
        skills[2] = "Athletics";
        skills[3] = "Arcana";
        skills[4] = "Deception";
        skills[5] = "History";
        skills[6] = "Intimidation";
        skills[7] = "Investigation";
        skills[8] = "Medicine";
        skills[9] = "Nature";
        skills[10] = "Perception";
        skills[11] = "Performance";
        skills[12] = "Persuasion";
        skills[13] = "Sleight of Hand";
        skills[14] = "Stealth";
        skills[15] = "Survival";
        statTypes.add(skills);
        statTypes.add(new String[]{"Proficiency"});

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
