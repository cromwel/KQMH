package com.kqmh.app.kqmh.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.kqmh.app.kqmh.Models.OrganisationUnit;
import com.kqmh.app.kqmh.R;


import java.util.ArrayList;
import java.util.List;


public class OrganisationUnitAdapterEx extends ArrayAdapter<String> {
    private Context context;
    private List<String> organisationUnits = new ArrayList<>();


    public OrganisationUnitAdapterEx(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public OrganisationUnitAdapterEx(@NonNull Context context, int resource, List<String> organisationUnits) {
        super(context, resource);
        this.context = context;
        this.organisationUnits = organisationUnits;
    }

    @Override
    public int getCount() {
        return organisationUnits.size();
    }

    @Override
    public String getItem(int position) {
        return organisationUnits.get(position);
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
        label.setText(organisationUnits.get(position));

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(organisationUnits.get(position));

        return label;
    }

}
