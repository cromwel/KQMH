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

import com.kqmh.app.kqmh.Models.CountyModel;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class CountyAdapter extends ArrayAdapter<CountyModel> {
    private Context context;
    private List<CountyModel> county ;

    public CountyAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.county = SQLite.select()
                .from(CountyModel.class)
                .queryList();
    }

    @Override
    public int getCount() {
        Log.d("Size", String.valueOf(county.size()));
        return county.size();
    }

    @Override
    public CountyModel getItem(int position) {
        return county.get(position);
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
        Log.d("County Name", county.get(position).getName());
        label.setText(county.get(position).getName());

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(county.get(position).getName());

        return label;
    }
}
