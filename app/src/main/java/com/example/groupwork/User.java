package com.example.groupwork;

import java.util.ArrayList;

public class User {

    public String userName;
    public ArrayList<String> friendsList;
    public ArrayList<StickerMessage> messageList;

    public User(String name){
        this.userName = name;
        friendsList = new ArrayList<>();
        messageList = new ArrayList<>();
    }
}