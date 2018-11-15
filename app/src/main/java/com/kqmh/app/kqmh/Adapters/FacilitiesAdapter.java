package com.kqmh.app.kqmh.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.kqmh.app.kqmh.Models.FacilityUnit;

import java.util.ArrayList;
import java.util.List;

public class FacilitiesAdapter extends ArrayAdapter<FacilityUnit> {

    private Context context;
    private List<FacilityUnit> facilitiesList = new ArrayList<>();


    public FacilitiesAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public FacilitiesAdapter(@NonNull Context context, int resource, List<FacilityUnit> facilitiesList) {
        super(context, resource);
        this.context = context;
        this.facilitiesList = facilitiesList;
    }

    @Override
    public int getCount() {
        return facilitiesList.size();
    }

    @Override
    public FacilityUnit getItem(int position) {
        return facilitiesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        CheckedTextView label = (CheckedTextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        Log.d("Facility", facilitiesList.get(position).getName());
        label.setText(facilitiesList.get(position).getName());

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(facilitiesList.get(position).getName());

        return label;
    }


}
