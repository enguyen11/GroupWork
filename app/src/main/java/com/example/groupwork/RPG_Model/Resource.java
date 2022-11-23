package com.example.groupwork.RPG_Model;

public class Resource {
    private String name;
    private String[] attributes;

    public Resource(String name, int num){
        this.name = name;
        this.attributes = new String[num];
    }
}
