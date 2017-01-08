package com.example.android.quakereport;

import static com.example.android.quakereport.R.id.date;
import static com.example.android.quakereport.R.id.mag;

/**
 * Created by gfibertester on 1/6/17.
 */

public class Earthquake {
    private double mMag;
    private String mCity;
    private Long mDate;
    private String mURL;

    /**
     *
     * @param mag
     * @param city
     * @param date
     */
    public Earthquake(double mag, String city, Long date, String URL) {
        mMag = mag;
        mCity = city;
        mDate = date;
        mURL = URL;
    }

    /**
     *
     * @return
     */
    public double getMag() {
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

    /**
     *
     * @return
     */
    public String getURL() {
        return mURL;
    }
}
