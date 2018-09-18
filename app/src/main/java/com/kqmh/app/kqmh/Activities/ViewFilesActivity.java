package com.kqmh.app.kqmh.Activities;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
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
import com.kqmh.app.kqmh.Adapters.ViewFilesAdapter;
import com.kqmh.app.kqmh.Forms.Assessment_Info;
import com.kqmh.app.kqmh.Forms.Dimensions_List;
import com.kqmh.app.kqmh.Models.OrganisationUnit;
import com.kqmh.app.kqmh.Models.ViewFiles;
import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.SessionManager;
import com.kqmh.app.kqmh.Utils.AuthBuilder;
import com.kqmh.app.kqmh.Utils.UrlConstants;
import com.kqmh.app.kqmh.Utils.VolleySingleton;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ViewFilesActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfiles);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Logging out...");
        progressDialog.setCancelable(false);

        Button start_assessment = findViewById(R.id.bt_start_assessment);
        start_assessment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit_start_assessment();
            }
        });

        

        // if extending Activity
        setContentView(R.layout.activity_viewfiles);

        // 1. pass context and data to the custom adapter
        ViewFilesAdapter adapter = new ViewFilesAdapter(this, generateData());

        // 2. Get ListView from activity_main.xml
        ListView listView = (ListView) findViewById(R.id.listview);

        // 3. setListAdapter if extending Activity
        listView.setAdapter(adapter);
       // setListAdapter(adapter);


    }

    private ArrayList<ViewFiles> generateData(){
        ArrayList<ViewFiles> models = new ArrayList<ViewFiles>();
        models.add(new ViewFiles("Files for Upload"));
        models.add(new ViewFiles(R.drawable.cloud,"Data Entry on 1st Q","UPLOAD"));
        models.add(new ViewFiles(R.drawable.cloud,"Data Entry on 2nd Q","UPLOAD"));
        models.add(new ViewFiles(R.drawable.cloud,"Data Entry on 3rd Q","UPLOAD"));
        models.add(new ViewFiles(R.drawable.cloud,"Data Entry on 1st Q","UPLOAD"));

        return models;
    }


    String data = "{\n" +
            "  \"dataSet\": \"TA4FU3zu93l\",\n" +
            "  \"completeDate\": \"2018-02-02\",\n" +
            "  \"period\": \"201801\",\n" +
            "  \"orgUnit\": \"TA4FU3zu93l\",\n" +
            "  \"attributeOptionCombo\": \"TA4FU3zu93l\",\n" +
            "  \"dataValues\": [\n" +
            "    { \"dataElement\": \"0wEDb8DsuJ\", \"categoryOptionCombo\": \"K9yYBM4Ejcl\", \"value\": \"1\", \"comment\": \"comment1\" },\n" +
            "  ]\n" +
            "}";

    public void submit_file() {
        OrganisationUnit orgUnit = SQLite.select()
                .from(OrganisationUnit.class)
                .querySingle();
        if (orgUnit != null) {
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(data);
                SessionManager sessionManager = new SessionManager(getBaseContext());
                try {
                    send(AuthBuilder.encode(sessionManager.getUserName(), sessionManager.getPassword()), jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (JSONException e) {
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



    public void submit_start_assessment(){
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
