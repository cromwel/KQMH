package com.kqmh.app.kqmh.Activities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.kqmh.app.kqmh.Adapters.ProgressAdapter;
import com.kqmh.app.kqmh.Adapters.ViewFilesAdapter;
import com.kqmh.app.kqmh.Forms.Assessment_Info;
import com.kqmh.app.kqmh.Forms.Dimensions_List;
import com.kqmh.app.kqmh.Models.AbstractOrgUnit;
import com.kqmh.app.kqmh.Models.AbstractOrgUnit_Table;
import com.kqmh.app.kqmh.Models.AssesmentProgress;
import com.kqmh.app.kqmh.Models.DataElement;
import com.kqmh.app.kqmh.Models.DataElement_Table;
import com.kqmh.app.kqmh.Models.OrganisationUnit;
import com.kqmh.app.kqmh.Models.Period;
import com.kqmh.app.kqmh.Models.ViewFiles;
import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.SessionManager;
import com.kqmh.app.kqmh.Utils.AuthBuilder;
import com.kqmh.app.kqmh.Utils.MyDatabase;
import com.kqmh.app.kqmh.Utils.UrlConstants;
import com.kqmh.app.kqmh.Utils.VolleySingleton;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.kqmh.app.kqmh.Models.Period_Table.period;


public class ViewFilesActivity extends AppCompatActivity {

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
                submit_file();
            }
        });

        Button start_assessment = findViewById(R.id.bt_start_assessment);
        start_assessment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit_start_assessment();
            }
        });

        List<AssesmentProgress> progresses = SQLite.select().from(AssesmentProgress.class).queryList();
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(new ProgressAdapter(getBaseContext(), progresses));


    }


    public static JSONObject toJSon() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-mm-DD", Locale.getDefault());
        Date date = new Date();
        String now = dateFormat.format(date);
        try {
            JSONObject dataset = new JSONObject();
            List<DataElement> elements = SQLite.select()
                    .from(DataElement.class)
                    .queryList();

            AbstractOrgUnit orgUnit = SQLite.select()
                    .from(AbstractOrgUnit.class).querySingle();

            Period periodT = SQLite.select()
                    .from(Period.class).querySingle();

            JSONArray datavaluesarray = new JSONArray();

            for (DataElement dataElement : elements) {
                if (dataElement.getValue() != null) {
                    JSONObject dataElementObj = new JSONObject();
                    dataElementObj.put("dataElement", dataElement.getDataElementId());
                    dataElementObj.put("value", dataElement.getValue());

                    datavaluesarray.put(dataElementObj);
                }
            }

            dataset.put("dataSet", "TA4FU3zu93l");
            dataset.put("completeDate", now);
            if (periodT != null) {
                dataset.put("period", periodT.getId());
            }
            if (orgUnit != null) {
                dataset.put("orgUnit", orgUnit.getId());
            }
            dataset.put("dataValues", datavaluesarray);


            return dataset;

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return null;

    }


    public void submit_file() {
        OrganisationUnit orgUnit = SQLite.select()
                .from(OrganisationUnit.class)
                .querySingle();
        if (orgUnit != null) {
            JSONObject jsonObject;
            jsonObject = toJSon();
            Log.d("json", jsonObject.toString());
            SessionManager sessionManager = new SessionManager(getBaseContext());
            try {
                send(AuthBuilder.encode(sessionManager.getUserName(), sessionManager.getPassword()), jsonObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void send(final String encoded, JSONObject jsonObject) {
        progressDialog.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, UrlConstants.SEND_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());
                closeProgressbar();
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                closeProgressbar();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                //headers.put("Content-Type","application/json");
                Log.d("Encoded", encoded);
                headers.put("Authorization", encoded);
                return headers;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }


    public void submit_start_assessment() {
        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), Assessment_Info.class);
        startActivity(intent);

    }


    private void closeProgressbar() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
