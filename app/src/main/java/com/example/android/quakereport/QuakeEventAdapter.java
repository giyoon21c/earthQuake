package com.example.android.quakereport;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DecimalFormat;

import java.util.ArrayList;

import static android.R.attr.format;
import static com.example.android.quakereport.R.id.date;
import android.graphics.drawable.GradientDrawable;


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

    private String getMagFormat(double mag) {
        DecimalFormat magFormat = new DecimalFormat("0.0");
        return magFormat.format(mag);
    }

    private int getMagnitudeColor(double mag) {
        int magnitudeColor = (int) Math.floor(mag);

        switch((int)mag) {
            case 0: case 1:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude1);
                break;
            case 2:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude2);
                break;
            case 3:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude3);
                break;
            case 4:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude4);
                break;
            case 5:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;
            case 6:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude6);
                break;
            case 7:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude7);
                break;
            case 8:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude8);
                break;
            case 9:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude9);
                break;
            default:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
                break;
        }
        return magnitudeColor;
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

        double mag = currentQuake.getMag();

        TextView magTextView = (TextView) listItemView.findViewById(R.id.mag);
        magTextView.setText(getMagFormat(mag));

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

        // setting the mag color - make a note of this section
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentQuake.getMag());
        magnitudeCircle.setColor(magnitudeColor);

        return listItemView;
    }
}
