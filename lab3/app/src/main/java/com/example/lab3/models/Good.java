package com.example.lab3.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.xml.sax.Parser;

public class Good implements Parcelable {
    int id;
    String name;

    String price;
    boolean check;

    public Good(int id, String name, String price, boolean check){
        this.id = id;
        this.name = name;
        this.price = price;
        this.check = check;
    }

    protected Good(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readString();
        check = in.readByte() != 0;
    }

    public static final Creator<Good> CREATOR = new Creator<Good>() {
        @Override
        public Good createFromParcel(Parcel in) {
            return new Good(in);
        }

        @Override
        public Good[] newArray(int size) {
            return new Good[size];
        }
    };

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public boolean getCheck(){
        return this.check;
    }

    public String getPrice(){ return  this.price; }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public void setPrice(String price){ this.price = price; }

    public void triggerCheck(){ this.check = !this.check; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(price);
        parcel.writeByte((byte) (check ? 1 : 0));
    }
}
