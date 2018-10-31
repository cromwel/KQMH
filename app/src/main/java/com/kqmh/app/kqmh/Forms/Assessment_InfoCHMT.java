package com.kqmh.app.kqmh.Forms;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.google.gson.Gson;
import com.kqmh.app.kqmh.Activities.Welcome;
import com.kqmh.app.kqmh.Adapters.OrganisationUnitAdapter;
import com.kqmh.app.kqmh.Adapters.OrganisationUnitAdapterChmt;
import com.kqmh.app.kqmh.Adapters.OrganisationUnitAdapterEx;
import com.kqmh.app.kqmh.Adapters.PeriodAdapter;
import com.kqmh.app.kqmh.Models.AbstractOrgUnit;
import com.kqmh.app.kqmh.Models.AssessmentTypeCombo;
import com.kqmh.app.kqmh.Models.FacilityLevel;
import com.kqmh.app.kqmh.Models.KeyValue;
import com.kqmh.app.kqmh.Models.OrganisationUnit;
import com.kqmh.app.kqmh.Models.Period;
import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.SessionManager;
import com.kqmh.app.kqmh.Utils.AuthBuilder;
import com.kqmh.app.kqmh.Utils.UrlConstants;
import com.kqmh.app.kqmh.Utils.VolleySingleton;
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

import static com.kqmh.app.kqmh.Utils.UrlConstants.ORGANISATION_UNIT_URL_ex;

public class Assessment_InfoCHMT extends AppCompatActivity {

    List<AssessmentTypeCombo> categoryOptions = new ArrayList<>();
    List<OrganisationUnit> OrganisationUnit = new ArrayList<>();
    List<String> orgUnitsNames = new ArrayList<>();
    List<Period> qPeriod = new ArrayList<>();

    private Spinner spinner_county;
    private SearchableSpinner spinner_OrganisationUnit;
    private Spinner spinner_AssessmentType;
    private Spinner spinner_period;
    private Spinner spinner_facilityLevel;
    private ImageView logout;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_assessment_info_chmt);

        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        spinner_county = findViewById(R.id.spinner_county);
        spinner_OrganisationUnit = findViewById(R.id.spinner_OrganisationUnit);
        spinner_OrganisationUnit.setTitle("Organizational Units");
        spinner_OrganisationUnit.setPositiveButton("Cancel");
        //spinner_AssessmentType = findViewById(R.id.spinner_AssessmentType);
        spinner_period = findViewById(R.id.spinner_period);
        spinner_facilityLevel = findViewById(R.id.spinner_facilityLevel);

        logout = findViewById(R.id.img_logout);

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

        SessionManager sessionManager = new SessionManager(getBaseContext());

        /*try {
            getAssessmentType(AuthBuilder.encode(sessionManager.getUserName(), sessionManager.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();


        }*/
        try {
            for (int i = 1; i < 350; i++) {
                String url = ORGANISATION_UNIT_URL_ex.replace("[number]", String.valueOf(i));
                getOrganisationUnit(AuthBuilder.encode(sessionManager.getUserName(), sessionManager.getPassword()), url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            AbstractOrgUnit coUnit = SQLite.select()
                    .from(AbstractOrgUnit.class)
                    .querySingle();
            if (coUnit != null) {
                String url = UrlConstants.ORGANISATION_UNIT_URL + coUnit.getId();
                getCountyUnit(AuthBuilder.encode(sessionManager.getUserName(), sessionManager.getPassword()), url);
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


    /*county*/
    public void spinnerData_CountyUnit(Spinner spinner_county, final String choice) {
        //fill data in spinner
        OrganisationUnitAdapterChmt adapter = new OrganisationUnitAdapterChmt(this, android.R.layout.simple_spinner_dropdown_item);
        spinner_county.setAdapter(adapter);
        spinner_county.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void getCountyUnit(final String encoded, final String url) {
        progressDialog.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Log.d("organisation unit", response.toString());
                try {
                    Gson gson = new Gson();
                    OrganisationUnit org = gson.fromJson(response.toString(), OrganisationUnit.class);
                    org.save();
                    orgUnitsNames.add(org.getDisplayName());
                    spinnerData_CountyUnit(spinner_county, "1");
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
      //  Log.d("Saving units", "saving " + OrganisationUnit.size());
        for (OrganisationUnit organisationUnit : OrganisationUnit) {
            organisationUnit.save();
        }
    }


    /*Orgs*/
    public void spinnerData_OrganisationUnit(Spinner spinner_OrganisationUnit, final String choice) {
        //fill data in spinner
        OrganisationUnitAdapterEx adapter = new OrganisationUnitAdapterEx(this, android.R.layout.simple_spinner_dropdown_item, orgUnitsNames);
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
        Log.d("Auth", encoded);
        progressDialog.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               // Log.d("organisation unit", response.toString());
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


    /*period*/
    public void spinnerData_period(Spinner spinner, final String choice) {

        qPeriod.add(new Period(201800, "select"));
        qPeriod.add(new Period(201804, "October - December 2018"));
        qPeriod.add(new Period(201803, "July - September 2018"));
        qPeriod.add(new Period(201802, "April - June 2018"));
        qPeriod.add(new Period(201801, "January - March 2018"));

        PeriodAdapter adapter = new PeriodAdapter(this, android.R.layout.simple_spinner_dropdown_item, qPeriod);
        spinner_period.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void savePeriod(List<Period> qPeriod) {
        Log.d("Saving quarter", "saving " + qPeriod.size());
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
                Intent intent = new Intent(getBaseContext(), Welcome.class);
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