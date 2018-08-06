package com.kqmh.app.kqmh.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.kqmh.app.kqmh.Models.Option;
import com.kqmh.app.kqmh.R;

import java.util.ArrayList;
import java.util.List;


public class ScoreOptionstAdapter2 extends ArrayAdapter<Option> {
    private Context context;
    private List<Option> options = new ArrayList<>();


    public ScoreOptionstAdapter2(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public ScoreOptionstAdapter2(@NonNull Context context, int resource, List<Option> options) {
        super(context, resource);
        this.context = context;
        this.options = options;
    }

    @Override
    public int getCount() {
        return options.size();
    }

    @Override
    public Option getItem(int position) {
        return options.get(position);
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
        label.setTextColor(ContextCompat.getColor(context, R.color.orange));
        label.setText(options.get(position).getName());

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(options.get(position).getName());

        return label;
    }

}
