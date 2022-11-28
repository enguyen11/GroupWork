package com.example.groupwork.StickerActivity;

import com.example.groupwork.StickerActivity.StickerMessage;

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
