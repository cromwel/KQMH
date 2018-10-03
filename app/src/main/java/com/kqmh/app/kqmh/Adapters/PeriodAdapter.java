package com.kqmh.app.kqmh.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.kqmh.app.kqmh.Models.Period;

import java.util.ArrayList;
import java.util.List;

public class PeriodAdapter extends ArrayAdapter<Period> {
    private Context context;
    private List<Period> periodList = new ArrayList<>();

    public PeriodAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public PeriodAdapter(@NonNull Context context, int resource, List<Period> periodType) {
        super(context, resource);
        this.periodList = periodType;
    }

    @Override
    public int getCount() {
        return periodList.size();
    }

    @Override
    public Period getItem(int position) {
        return periodList.get(position);
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
        label.setText(periodList.get(position).getPeriod());

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(periodList.get(position).getPeriod());

        return label;
    }
}
