package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class QuakeEventAdapter extends ArrayAdapter<Earthquake>{

    /**
     *
     * @param context
     * @param earthquakes
     */
    public QuakeEventAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    /**
     *
     * @param position
     * @param currentView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View currentView, ViewGroup parent) {

        View listItemView = currentView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.quake_item, parent, false);
        }

        Earthquake currentQuake = getItem(position);

        TextView magTextView = (TextView) listItemView.findViewById(R.id.mag);
        magTextView.setText(currentQuake.getMag());

        TextView cityTextView = (TextView) listItemView.findViewById(R.id.city);
        cityTextView.setText(currentQuake.getCity());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        dateTextView.setText(currentQuake.getDate());

        return listItemView;
    }
}
