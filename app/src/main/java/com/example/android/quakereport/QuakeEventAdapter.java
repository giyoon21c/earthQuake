package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gfibertester on 1/6/17.
 */

public class QuakeEventAdapter extends ArrayAdapter<Quake>{

    public QuakeEventAdapter(Activity context, ArrayList<Quake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent) {

        View listItemView = currentView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.quake_item, parent, false);
        }

        Quake currentQuake = getItem(position);

        TextView magTextView = (TextView) listItemView.findViewById(R.id.mag);
        magTextView.setText(""+currentQuake.getMag());

        TextView cityTextView = (TextView) listItemView.findViewById(R.id.city);
        cityTextView.setText(currentQuake.getCity());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        dateTextView.setText(currentQuake.getDate());

        return listItemView;
    }
}
