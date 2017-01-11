/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.x;
import static android.media.CamcorderProfile.get;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String USGS_REQUEST_URL =
            "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        /*
        ArrayList<Quake> earthquakes = new ArrayList<Quake>();

        earthquakes.add(new Quake("7.2", "San Francisco", "Feb 2, 2016"));
        earthquakes.add(new Quake("6.1", "London", "July 20, 2015"));
        earthquakes.add(new Quake("3.9", "Tokyo", "Nov 10 2014"));
        earthquakes.add(new Quake("5.4", "Mexico City", "May 3, 2014"));
        earthquakes.add(new Quake("2.8", "Moscow", "Jan 31, 2013"));
        earthquakes.add(new Quake("4.9", "Rio de Janeiro", "Aug 19, 2012"));
        earthquakes.add(new Quake("1.6", "Paris", "Oct 20 2011"));
        */

        //final ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();
        //final ArrayList<Earthquake> earthquakes;

        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(USGS_REQUEST_URL);

    }

    void updateUi(final List<Earthquake> earthquakes) {

        QuakeEventAdapter quakeAdapter = new QuakeEventAdapter(this, (ArrayList)earthquakes);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(quakeAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter,
                                    View view,
                                    int position,
                                    long id) {
                Toast.makeText(getApplicationContext(), "sent intent " + position,
                        Toast.LENGTH_SHORT).show();
                Earthquake quake = earthquakes.get(position);
                Log.v("Earthquake", "Current quake: " + quake.getURL());

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(quake.getURL()));
                startActivity(i);
            }
        });
    }

    /*
     create a async task which will go to a url and download json files and put them
     in ArrayList<Earthquake>
    */
    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>> {

        @Override
        protected List<Earthquake> doInBackground(String... urls) {
            List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(urls[0]);
            return earthquakes;
        }

        @Override
        protected void onPostExecute(List<Earthquake> earthquakes) {
            if (earthquakes == null) {
                return;
            }
            updateUi(earthquakes);
        }
    }
}
