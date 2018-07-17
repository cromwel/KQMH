package com.kqmh.app.kqmh.Forms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;

import com.google.gson.Gson;

import com.kqmh.app.kqmh.Adapters.AssessmentTypeAdapter;
import com.kqmh.app.kqmh.Adapters.OrganisationUnitAdapter;
import com.kqmh.app.kqmh.Models.AssessmentTypeCombo;
import com.kqmh.app.kqmh.Models.OrganisationUnit;
import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.SessionManager;
import com.kqmh.app.kqmh.Utils.AuthBuilder;
import com.kqmh.app.kqmh.Utils.UrlConstants;
import com.kqmh.app.kqmh.Utils.VolleySingleton;
import com.kqmh.app.kqmh.Models.KeyValue;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kqmh.app.kqmh.Utils.UrlConstants.ORGANISATION_UNIT_URL;

public class Assessment_Info extends AppCompatActivity {

    private SearchableSpinner spinner_OrganisationUnit;
    private Spinner spinner_AssessmentType;
    private Spinner spinner_period;
    private Spinner spinner_facilityLevel;
    private ProgressDialog progressDialog;
    List<AssessmentTypeCombo> categoryOptions = new ArrayList<>();
    List<OrganisationUnit> OrganisationUnit = new ArrayList<>();
    List<String> orgUnitsNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_assessment_info);

        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        spinner_OrganisationUnit = findViewById(R.id.spinner_OrganisationUnit);
        spinner_OrganisationUnit.setTitle("Organizational Units");
        spinner_OrganisationUnit.setPositiveButton("Cancel");
        spinner_AssessmentType = findViewById(R.id.spinner_AssessmentType);
        spinner_period = findViewById(R.id.spinner_period);
        spinner_facilityLevel = findViewById(R.id.spinner_facilityLevel);

        spinnerData_period(spinner_period, "1");
        spinnerData_facilityLevel(spinner_facilityLevel, "1");

        Button dataEntry = findViewById(R.id.bt_dataEntry);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("loading form...");
        progressDialog.setCancelable(false);
        dataEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        List<AssessmentTypeCombo> employees = SQLite.select()
                .from(AssessmentTypeCombo.class)
                .queryList();
        Toast.makeText(getBaseContext(),"Pulled from db " + employees.size(),Toast.LENGTH_LONG).show();

        SessionManager sessionManager = new SessionManager(getBaseContext());
        try {
            getAssessmentType(AuthBuilder.encode(sessionManager.getUserName(), sessionManager.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            for(int i=1;i<350;i++) {
                String url = ORGANISATION_UNIT_URL.replace("[number]", String.valueOf(i));
                getOrganisationUnit(AuthBuilder.encode(sessionManager.getUserName(), sessionManager.getPassword()), url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public void spinnerData_OrganisationUnit(Spinner spinner_OrganisationUnit, final String choice) {
        //fill data in spinner
        OrganisationUnitAdapter adapter = new OrganisationUnitAdapter(this, android.R.layout.simple_spinner_dropdown_item, orgUnitsNames);
        spinner_OrganisationUnit.setAdapter(adapter);
        spinner_OrganisationUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (choice.matches("1")) {
                }
                else if (choice.matches("2")) {
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getOrganisationUnit(final String encoded,final String url) {
        Log.d("Auth", encoded);
        progressDialog.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("organisation unit", response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray("organisationUnits");
                    Gson gson = new Gson();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        OrganisationUnit org = gson.fromJson(jsonArray.getJSONObject(i).toString(), OrganisationUnit.class);
                        OrganisationUnit.add(org);
                        orgUnitsNames.add(org.getDisplayName());
                    }
                    closeProgressbar();
                    spinnerData_OrganisationUnit(spinner_OrganisationUnit, "1");
                } catch (JSONException e) {
                    e.printStackTrace();
                    closeProgressbar();
                }
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

    public void spinnerData_AssessmentType(Spinner spinner_AssessmentType, final String choice) {
        //fill data in spinner
        AssessmentTypeAdapter adapter = new AssessmentTypeAdapter(this,android.R.layout.simple_spinner_dropdown_item,categoryOptions);
        spinner_AssessmentType.setAdapter(adapter);
        spinner_AssessmentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (choice.matches("1")) {
                }
                else if (choice.matches("2")) {
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getAssessmentType(final String encoded) {
        Log.d("Auth", encoded);
        progressDialog.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, UrlConstants.ASSESSMENT_TYPE_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("assessment type", response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray("categoryOptionCombos");
                    Gson gson = new Gson();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        AssessmentTypeCombo combo = gson.fromJson(jsonArray.getJSONObject(i).toString(), AssessmentTypeCombo.class);
                        categoryOptions.add(combo);
                    }
                    saveOptions(categoryOptions);
                    closeProgressbar();
                    spinnerData_AssessmentType(spinner_AssessmentType, "1");
                } catch (JSONException e) {
                    e.printStackTrace();
                    closeProgressbar();
                }
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

    private void saveOptions(List<AssessmentTypeCombo> categoryOptions) {
        Log.d("Saving options", "saving " + categoryOptions.size());
        for(AssessmentTypeCombo assessmentTypeCombo : categoryOptions){
            assessmentTypeCombo.save();
        }
    }

    public void spinnerData_period(Spinner spinner, final String choice) {
        ArrayList<KeyValue> keyvalue = new ArrayList<>();

        // adding each child node to HashMap key => value
        keyvalue.add(new KeyValue(0, "Select"));
        keyvalue.add(new KeyValue(1, "April - June 2018"));
        keyvalue.add(new KeyValue(2, "January - March 2018"));


        //fill data in spinner
        ArrayAdapter<KeyValue> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, keyvalue);
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //occupationSpinner.setSelection(adapter.getPosition(keyvalue.get(2)));//Optional to set the selected item.

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                KeyValue value = (KeyValue) parent.getSelectedItem();
                if (choice.matches("1")) {
                    //occupation = value.getId();
                } else if (choice.matches("2")) {
                    //occupation = value.getId();
                }
                //updateValues();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void spinnerData_facilityLevel(Spinner spinner, final String choice) {
        ArrayList<KeyValue> keyvalue = new ArrayList<>();

        // adding each child node to HashMap key => value
        keyvalue.add(new KeyValue(0, "Select"));
        keyvalue.add(new KeyValue(1, "level 6"));
        keyvalue.add(new KeyValue(2, "level 5"));
        keyvalue.add(new KeyValue(3, "level 4 with dialysis"));
        keyvalue.add(new KeyValue(4, "level 4"));
        keyvalue.add(new KeyValue(5, "level 3"));
        keyvalue.add(new KeyValue(6, "level 2"));


        //fill data in spinner
        ArrayAdapter<KeyValue> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, keyvalue);
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //occupationSpinner.setSelection(adapter.getPosition(keyvalue.get(2)));//Optional to set the selected item.

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                KeyValue value = (KeyValue) parent.getSelectedItem();
                if (choice.matches("1")) {
                    //occupation = value.getId();
                } else if (choice.matches("2")) {
                    //occupation = value.getId();
                }
                //updateValues();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void submit() {
        closeProgressbar();
        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), Dimensions_List.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void closeProgressbar() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}