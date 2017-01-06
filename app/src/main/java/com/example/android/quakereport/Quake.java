package com.example.android.quakereport;

/**
 * Created by gfibertester on 1/6/17.
 */

public class Quake {
    double mMag;
    String mCity;
    String mDate;

    public Quake(double mag, String city, String date) {
        mMag = mag;
        mCity = city;
        mDate = date;
    }

    public double getMag() {
        return mMag;
    }


    public String getCity() {
        return mCity;
    }

    public String getDate() {
        return mDate;
    }
}
