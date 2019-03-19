package com.kqmh.app.kqmh.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.kqmh.app.kqmh.Adapters.ProgressAdapter;
import com.kqmh.app.kqmh.Forms.Dimensions_List;
import com.kqmh.app.kqmh.Models.AssesmentProgress;
import com.kqmh.app.kqmh.Network.Merlin.presentation.DemoActivity;
import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.SessionManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;


public class ProgressActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfiles);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Syncing data...");
        progressDialog.setCancelable(false);

        Button markcomplete = findViewById(R.id.bt_complete);
        markcomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complete();
            }
        });


        List<AssesmentProgress> progresses = SQLite.select().from(AssesmentProgress.class).queryList();
       /* RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(new ProgressAdapter(getBaseContext(), progresses));*/

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prev_submit();
            }
        });


    }

    private void prev_submit() {
        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), Dimensions_List.class);
        startActivity(intent);
    }

    public void complete() {
        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), DemoActivity.class);
        startActivity(intent);
    }

    private void closeProgressbar() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
