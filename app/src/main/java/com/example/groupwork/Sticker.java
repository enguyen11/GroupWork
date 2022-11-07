package com.example.groupwork;

public class Sticker {
    private final String name;
    protected int numUse = 0;

    public Sticker(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public int getNumUse() {return numUse;}
}
