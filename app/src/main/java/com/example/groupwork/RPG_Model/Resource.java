package com.example.groupwork.RPG_Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Resource implements Parcelable {
    private String name;
   // private String[] attributes;

    public Resource(String name, int num){
        this.name = name;
       // this.attributes = new String[num];
    }
    public Resource(){

    }

    protected Resource(Parcel in) {
        name = in.readString();
       // attributes = in.createStringArray();
    }

    public static final Creator<Resource> CREATOR = new Creator<Resource>() {
        @Override
        public Resource createFromParcel(Parcel in) {
            return new Resource(in);
        }

        @Override
        public Resource[] newArray(int size) {
            return new Resource[size];
        }
    };

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
   // public String[] getAttributes(){
       // return this.attributes;
   // }
    //public void setAttributes(String[] attributes){
      //  this.attributes = attributes;
   // }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
       // parcel.writeStringArray(attributes);
    }
}
