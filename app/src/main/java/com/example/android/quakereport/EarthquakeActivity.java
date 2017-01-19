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
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.x;
import static android.media.CamcorderProfile.get;

public class EarthquakeActivity extends AppCompatActivity
        implements LoaderCallbacks<List<Earthquake>>{

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String USGS_REQUEST_URL =
            "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    private static final int EARTHQUAKE_LOADER_ID = 1;

    private QuakeEventAdapter mAdapter;
    private TextView mEmptyStateTextView;
    private ProgressBar mProgressBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        mAdapter = new QuakeEventAdapter(this, new ArrayList<Earthquake>());
        earthquakeListView.setAdapter(mAdapter);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(mEmptyStateTextView);

        mProgressBarView = (ProgressBar) findViewById(R.id.loading_spinner);

        // determine if there is an internet connection
        ConnectivityManager cm =
                (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean is_connected = (activeNetwork != null &&
                                activeNetwork.isConnectedOrConnecting());


        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter,
                                    View view,
                                    int position,
                                    long id) {
                Toast.makeText(getApplicationContext(), "sent intent " + position,
                        Toast.LENGTH_SHORT).show();
                Earthquake quake = mAdapter.getItem(position);
                Log.v("Earthquake", "Current quake: " + quake.getURL());

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(quake.getURL()));
                startActivity(i);
            }
        });

        if (is_connected) {
            getLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this);
        } else {
            mEmptyStateTextView.setText(R.string.no_internet);
        }
    }


    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bunel) {
        // TODO: create a new loader for the given URL
        return new EarthquakeLoader(EarthquakeActivity.this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader,List<Earthquake> earthquakes) {
        // TODO: update the UI with results

        mProgressBarView.setVisibility(View.GONE);

        mEmptyStateTextView.setText(R.string.no_earthquakes);

        if (earthquakes == null) {
            return;
        }
        mAdapter.clear();
        if (earthquakes != null && !earthquakes.isEmpty()) {
            // comment out below line to simulate lack of json response from http request
            mAdapter.addAll(earthquakes);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        // TODO: Loader reset, so we can clear out our existing data
        mAdapter.clear();
    }
}
