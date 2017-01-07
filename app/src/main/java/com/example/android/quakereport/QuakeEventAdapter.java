package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;

import static android.R.attr.format;
import static com.example.android.quakereport.R.id.date;


public class QuakeEventAdapter extends ArrayAdapter<Earthquake>{

    /**
     *
     * @param context
     * @param earthquakes
     */
    public QuakeEventAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }


    private String formatDate(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        String dateToDisplay = dateFormatter.format(date);
        return dateToDisplay;
    }


    private String formatTime(Date date) {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
        String timeToDisplay = timeFormatter.format(date);
        return timeToDisplay;
    }

    private String getCityOffset(String location) {
        if (location.contains("of")) {
            return location.substring(0, location.indexOf("of")+2);
        } else {
            return "Near";
        }
    }

    private String getCityPrimary(String location) {
        if (location.contains("of")) {
            return location.substring(location.indexOf("of")+3, location.length());
        } else {
            return location;
        }
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

        String location = currentQuake.getCity();
        TextView cityOffsetTextView = (TextView) listItemView.findViewById(R.id.city_offset);
        cityOffsetTextView.setText(getCityOffset(location));

        TextView cityPrimaryTextView = (TextView) listItemView.findViewById(R.id.city_primary);
        cityPrimaryTextView.setText(getCityPrimary(location));

        Date dateObject = new Date(currentQuake.getDate());
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        dateTextView.setText(formatDate(dateObject));

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        timeTextView.setText(formatTime(dateObject));

        return listItemView;
    }
}
