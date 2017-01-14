package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by gfibertester on 1/14/17.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private String mUrl;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
        // TODO:
    }

    @Override
    protected void onStartLoading(){
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        // TODO: implement this method
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }
}