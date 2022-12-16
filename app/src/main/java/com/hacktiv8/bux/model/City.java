package com.hacktiv8.bux.model;

import android.os.Parcel;
import android.os.Parcelable;

public class City implements Parcelable {
    private String id, city, terminal;

    public City(){

    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }


    public City(String id, String city, String terminal) {
        this.id = id;
        this.city = city;
        this.terminal = terminal;

    }

    protected City(Parcel in) {
        id = in.readString();
        city = in.readString();
        terminal = in.readString();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(city);
        parcel.writeString(terminal);
    }
}
