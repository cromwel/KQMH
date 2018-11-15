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

import com.kqmh.app.kqmh.Models.OrganisationUnit;
import com.kqmh.app.kqmh.Models.OrganisationUnit_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;


public class OrganisationUnitAdapter extends ArrayAdapter<OrganisationUnit> {
    private Context context;
    private List<OrganisationUnit> organisationUnits ;


    public OrganisationUnitAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.organisationUnits = SQLite.select()
                .from(OrganisationUnit.class)
                //.where(OrganisationUnit_Table.level.eq("5"))
                .queryList();

    }

    public OrganisationUnitAdapter(@NonNull Context context, int resource, List<String> organisationUnits) {
        super(context, resource);
        this.context = context;
        this.organisationUnits = SQLite.select()
                .from(OrganisationUnit.class)
                .where(OrganisationUnit_Table.level.eq("5"))
                .queryList();
    }

    @Override
    public int getCount() {
        Log.d("Size", String.valueOf(organisationUnits.size()));
        return organisationUnits.size();
    }

    @Override
    public OrganisationUnit getItem(int position) {
        return organisationUnits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        CheckedTextView label = (CheckedTextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        Log.d("Display name", organisationUnits.get(position).getDisplayName());
        label.setText(organisationUnits.get(position).getDisplayName());

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(organisationUnits.get(position).getDisplayName());

        return label;
    }

}
