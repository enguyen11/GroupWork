package com.example.groupwork.Firebase.StickerStuff;

import android.os.Parcel;
import android.os.Parcelable;

public class Sticker implements Parcelable {
    private final String name;
    private int numUse = 0;
    private String sender = "";

    public Sticker(String name) {
        this.name = name;
    }
    public Sticker(String name, int numUse) {
        this.name = name;
        this.numUse = numUse;
    }

    public String getName() {
        return name;
    }
    public int getNumUse() {return numUse;}
    public void setNumUse(int n) {numUse = n;}
    public String getSender() {return sender;}
    public void setSender(String s) {sender = s;}

    protected Sticker(Parcel in) {
        name = in.readString();
        numUse = in.readInt();
        sender = in.readString();
    }

    public static final Creator<Sticker> CREATOR = new Creator<Sticker>() {
        @Override
        public Sticker createFromParcel(Parcel in) {
            return new Sticker(in);
        }

        @Override
        public Sticker[] newArray(int size) {
            return new Sticker[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(numUse);
        dest.writeString(sender);
    }
}
