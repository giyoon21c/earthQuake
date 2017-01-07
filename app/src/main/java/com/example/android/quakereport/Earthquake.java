package com.example.android.quakereport;

/**
 * Created by gfibertester on 1/6/17.
 */

public class Earthquake {
    private String mMag;
    private String mCity;
    private String mDate;

    /**
     *
     * @param mag
     * @param city
     * @param date
     */
    public Earthquake(String mag, String city, String date) {
        mMag = mag;
        mCity = city;
        mDate = date;
    }

    /**
     *
     * @return
     */
    public String getMag() {
        return mMag;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return mCity;
    }

    /**
     *
     * @return
     */
    public String getDate() {
        return mDate;
    }
}
