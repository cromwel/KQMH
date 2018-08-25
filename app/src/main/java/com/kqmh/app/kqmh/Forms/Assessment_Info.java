package com.kqmh.app.kqmh.Forms;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;

import com.google.gson.Gson;

import com.kqmh.app.kqmh.Activities.Login;
import com.kqmh.app.kqmh.Activities.WelcomeActivity;
import com.kqmh.app.kqmh.Adapters.AssessmentTypeAdapter;
import com.kqmh.app.kqmh.Adapters.OrganisationUnitAdapter;
import com.kqmh.app.kqmh.Models.AbstractOrgUnit;
import com.kqmh.app.kqmh.Models.AssessmentTypeCombo;
import com.kqmh.app.kqmh.Models.OrganisationUnit;
import com.kqmh.app.kqmh.Models.Period;
import com.kqmh.app.kqmh.Models.FacilityLevel;
import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.SessionManager;
import com.kqmh.app.kqmh.Utils.AuthBuilder;
import com.kqmh.app.kqmh.Utils.ExportToJSON;
import com.kqmh.app.kqmh.Utils.UrlConstants;
import com.kqmh.app.kqmh.Utils.VolleySingleton;
import com.kqmh.app.kqmh.Models.KeyValue;
import com.kqmh.app.kqmh.Widgets.Facility_Box;
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

    List<AssessmentTypeCombo> categoryOptions = new ArrayList<>();
    List<Period> qPeriod = new ArrayList<>();
    List<FacilityLevel> levels = new ArrayList<>();
    List<String> orgUnitsNames = new ArrayList<>();
    private Spinner spinner_OrganisationUnit;
    private Spinner spinner_AssessmentType;
    private Spinner spinner_period;
    private Spinner spinner_facilityLevel;
    private ProgressDialog progressDialog;
    private ImageView logout;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_assessment_info);

        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        spinner_OrganisationUnit = findViewById(R.id.spinner_OrganisationUnit);

        spinner_AssessmentType = findViewById(R.id.spinner_AssessmentType);
        spinner_period = findViewById(R.id.spinner_period);
        spinner_facilityLevel = findViewById(R.id.spinner_facilityLevel);
        logout = findViewById(R.id.img_logout);

        spinnerData_period(spinner_period, "1");
        spinnerData_facilityLevel(spinner_facilityLevel, "1");
        coordinatorLayout = findViewById(R.id.coordinator);

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
        Toast.makeText(getBaseContext(), "Pulled from db " + employees.size(), Toast.LENGTH_LONG).show();

        SessionManager sessionManager = new SessionManager(getBaseContext());
        try {
            getAssessmentType(AuthBuilder.encode(sessionManager.getUserName(), sessionManager.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            AbstractOrgUnit orgUnit = SQLite.select()
                    .from(AbstractOrgUnit.class)
                    .querySingle();
            if (orgUnit != null) {
                String url = UrlConstants.ORGANISATION_UNIT_URL + orgUnit.getId();
                getOrganisationUnit(AuthBuilder.encode(sessionManager.getUserName(), sessionManager.getPassword()), url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(logout);
            }
        });
    }
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.home_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                logout();
                return true;
            }
        });
        popup.show();
    }
    /*org unit*/
    public void spinnerData_OrganisationUnit(Spinner spinner_OrganisationUnit, final String choice) {
        //fill data in spinner
        OrganisationUnitAdapter adapter = new OrganisationUnitAdapter(this, android.R.layout.simple_spinner_dropdown_item);
        spinner_OrganisationUnit.setAdapter(adapter);
        spinner_OrganisationUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (choice.matches("1")) {
                } else if (choice.matches("2")) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getOrganisationUnit(final String encoded, final String url) {
        progressDialog.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("organisation unit", response.toString());
                try {
                    Gson gson = new Gson();
                    OrganisationUnit org = gson.fromJson(response.toString(), OrganisationUnit.class);
                    org.save();
                    orgUnitsNames.add(org.getDisplayName());
                    spinnerData_OrganisationUnit(spinner_OrganisationUnit, "1");
                    closeProgressbar();
                } catch (Exception e) {
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

    private void saveUnits(List<OrganisationUnit> OrganisationUnit) {
        Log.d("Saving units", "saving " + OrganisationUnit.size());
        for (OrganisationUnit organisationUnit : OrganisationUnit) {
            organisationUnit.save();
        }
    }

    /*assessment type*/
    public void spinnerData_AssessmentType(Spinner spinner_AssessmentType, final String choice) {
        //fill data in spinner
        AssessmentTypeAdapter adapter = new AssessmentTypeAdapter(this, android.R.layout.simple_spinner_dropdown_item, categoryOptions);
        spinner_AssessmentType.setAdapter(adapter);
        spinner_AssessmentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (choice.matches("1")) {
                } else if (choice.matches("2")) {
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
        for (AssessmentTypeCombo assessmentTypeCombo : categoryOptions) {
            assessmentTypeCombo.save();
        }
    }

    /*period*/
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

    private void getPeriod(final String encoded) {

    }

    private void saveTime(List<Period> qPeriod) {
        Log.d("Saving time", "saving " + qPeriod.size());
        for (Period period : qPeriod) {
            period.save();
        }
    }

    /*facility level*/
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

    private void saveLevel(List<FacilityLevel> levels) {
        Log.d("Saving levels", "saving " + levels.size());
        for (FacilityLevel facilityLevel : levels) {
            facilityLevel.save();
        }
    }

    public void submit() {
        closeProgressbar();
        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), Dimensions_List.class);
        startActivity(intent);
    }

    private void closeProgressbar() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Log out?");
        builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                new SessionManager(getBaseContext()).setLoggedIn(false);
                Intent intent = new Intent(getBaseContext(), Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}