package com.kqmh.app.kqmh.Activities;

import java.text.SimpleDateFormat;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.kqmh.app.kqmh.Adapters.ProgressAdapter;
import com.kqmh.app.kqmh.Forms.Assessment_Info;
import com.kqmh.app.kqmh.Forms.Dimensions_List;
import com.kqmh.app.kqmh.Models.AbstractOrgUnit;
import com.kqmh.app.kqmh.Models.AssesmentProgress;
import com.kqmh.app.kqmh.Models.DataElement;
import com.kqmh.app.kqmh.Models.OrganisationUnit;
import com.kqmh.app.kqmh.Models.Period;
import com.kqmh.app.kqmh.Network.Merlin.presentation.DemoActivity;
import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.SessionManager;
import com.kqmh.app.kqmh.Utils.AuthBuilder;
import com.kqmh.app.kqmh.Utils.UrlConstants;
import com.kqmh.app.kqmh.Utils.VolleySingleton;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class ViewFilesActivity extends AppCompatActivity{

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
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(new ProgressAdapter(getBaseContext(), progresses));

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
