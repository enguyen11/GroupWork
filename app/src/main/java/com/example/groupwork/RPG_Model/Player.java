package com.example.groupwork.RPG_Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.groupwork.DNDChat.Message;
import com.example.groupwork.StickerActivity.StickerMessage;

//import org.checkerframework.checker.units.qual.A;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class Player implements Parcelable {

    private String username;
    private ArrayList<SheetType> sheets;
    private ArrayList<String> friends;
    private ArrayList<Character> characters;
    private ArrayList<Game> games;
    private ArrayList<Map> maps;
    private ArrayList<Message> messageList;
    private ArrayList<String> friendsList;

    public Player(){
        System.out.println("**************Making Player*********************");
    }
    public Player(String username){
        this.username = username;
        sheets = new ArrayList<>();
        messageList = new ArrayList<>();
        friendsList = new ArrayList<>();
        SheetType defaultSheet = makeDefault();
        this.sheets.add(defaultSheet);

    }
    public String getName(){
        return this.username;
    }
    public void setName(String name){
        this.username = name;
    }
    public void setSheets(ArrayList<SheetType> sheets){
        this.sheets = sheets;
    }
    public ArrayList<SheetType> getSheets(){
        return this.sheets;
    }
    public void addFriend(String friend) {
        this.friends.add(friend);
    }
    public void addFriendToMsgList(String friend) {
        this.friendsList.add(friend);
    }

    public void addMessageToList(Message myMessage){
        this.messageList.add(myMessage);
    }



    private SheetType makeDefault(){
        SheetType defaultSheet = new SheetType();
        defaultSheet.setName("D&D 5e Sheet");
        ArrayList<String> info = new ArrayList<>();
        ArrayList<String> statCats = new ArrayList<>();
        info.add("Name");
        info.add("Race");
        info.add("Class");
        info.add("Background");
        defaultSheet.setInfo(info);

        LinkedHashMap<String, ArrayList<String>> statList = new LinkedHashMap<>(100);
        ArrayList<String> level = new ArrayList<>(
                Arrays.asList("Level"));
        statList.put("Level", level);
        statCats.add("Level");

        ArrayList<String> hp = new ArrayList<>(
                Arrays.asList("Maximum Hit Points",
                        "Current Hit Points", "Temporary Hit Points", "Hit Dice")
        );
        statList.put("Hit Points", hp);
        statCats.add("Hit Points");

        ArrayList<String> ac = new ArrayList<>(
                Arrays.asList("Armor Class")
        );
        statList.put("Armor Class", ac);
        statCats.add("Armor Class");

        ArrayList<String> speed = new ArrayList<>(
                Arrays.asList("Walking Speed", "Swimming Speed",
                        "Climbing Speed", "Flying Speed")
        );
        statList.put("Speed", speed);
        statCats.add("Speed");

        ArrayList<String> init = new ArrayList<>(
                Arrays.asList("Initiative")
        );
        statList.put("Initiative", init);
        statCats.add("Initiative");

        ArrayList<String> abilities = new ArrayList<>(
                Arrays.asList("Strength", "Dexterity", "Constitution",
                        "Intelligence", "Wisdom", "Charisma")
        );
        statList.put("Ability Scores", abilities);
        statCats.add("Ability Scores");

        ArrayList<String> modifiers = new ArrayList<>(
                Arrays.asList("Strength", "Dexterity", "Constitution",
                        "Intelligence", "Wisdom", "Charisma")
        );
        statList.put("Ability Modifiers", modifiers);
        statCats.add("Ability Modifiers");

        ArrayList<String> skills = new ArrayList<>(
                Arrays.asList("Acrobatics", "Athletics", "Arcana",
                        "Deception", "History", "Intimidation",
                        "Investigation", "Medicine", "Nature",
                        "Perception", "Performance", "Persuasion",
                        "Sleight of Hand", "Stealth", "Survival")
        );
        statList.put("Skills", skills);
        statCats.add("Skills");
        ArrayList<String> prof = new ArrayList<>(
                Arrays.asList("Proficiency Bonus")
        );
        statList.put("Proficiency Bonus", prof);
        statCats.add("Proficiency Bonus");

        defaultSheet.setStats(statList);
        defaultSheet.setStatCats(statCats);

        LinkedHashMap<String, Resource> resourceList = new LinkedHashMap<>(100);
        Resource money = new Resource("Currency", 5);
       // money.setAttributes(new String[]{"cp", "sp", "ep", "gp", "pp"});
        resourceList.put("Currency", money);

        Resource attacks = new Resource("Attacks", 4);
       // attacks.setAttributes(new String[]{"Name", "Attack Bonus", "Damage", "Type"});
        resourceList.put("Attacks", attacks);
        defaultSheet.setResources(resourceList);
        return defaultSheet;

    }



    protected Player(Parcel in) {
        friends = in.createStringArrayList();  //in.createTypedArrayList(String);
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



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        //parcel.writeTypedList(friends);
        parcel.writeStringList(friends);
        parcel.writeTypedList(sheets);
    }
}
