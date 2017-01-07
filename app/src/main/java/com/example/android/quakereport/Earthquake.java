package com.example.android.quakereport;

import static com.example.android.quakereport.R.id.date;

/**
 * Created by gfibertester on 1/6/17.
 */

public class Earthquake {
    private String mMag;
    private String mCity;
    private Long mDate;

    /**
     *
     * @param mag
     * @param city
     * @param date
     */
    public Earthquake(String mag, String city, Long date) {
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
    public Long getDate() {
        return mDate;
    }
}
