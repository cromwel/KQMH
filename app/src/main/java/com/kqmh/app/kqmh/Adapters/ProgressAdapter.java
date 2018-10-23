package com.kqmh.app.kqmh.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kqmh.app.kqmh.Models.AssesmentProgress;
import com.kqmh.app.kqmh.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by ekirapa on 10/23/18 .
 */
public class ProgressAdapter extends RecyclerView.Adapter<ProgressAdapter.ProgressHolder> {
    private Context context;
    private List<AssesmentProgress> progresses = new ArrayList<>();

    public ProgressAdapter(Context context, List<AssesmentProgress> progresses) {
        this.context = context;
        this.progresses = progresses;
    }

    @Override
    public ProgressHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_progress, parent, false);
        return new ProgressHolder(v);
    }

    @Override
    public void onBindViewHolder(ProgressHolder holder, int position) {
        AssesmentProgress progress = progresses.get(position);
        holder.name.setText(progress.getDisplayName());
        holder.val.setText(String.format(Locale.getDefault(), "%d/%d", progress.getProgress(), progress.getMax()));
    }

    @Override
    public int getItemCount() {
        return progresses.size();
    }

    class ProgressHolder extends RecyclerView.ViewHolder {
        private TextView name, val;

        public ProgressHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            val = itemView.findViewById(R.id.tv_value);
        }
    }
}
