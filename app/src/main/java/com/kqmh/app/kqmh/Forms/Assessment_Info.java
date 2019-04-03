package com.kqmh.app.kqmh.Forms;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.google.gson.Gson;
import com.kqmh.app.kqmh.Activities.Welcome;
import com.kqmh.app.kqmh.Adapters.OrganisationUnitAdapter;
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

import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        //spinner_AssessmentType = findViewById(R.id.spinner_AssessmentType);
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
        /*try {
            getAssessmentType(AuthBuilder.encode(sessionManager.getUserName(), sessionManager.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try {
            //getPeriod(AuthBuilder.encode(sessionManager.getUserName(), sessionManager.getPassword()));
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

    /*period*/

    public void spinnerData_period(Spinner spinner, final String choice) {

        qPeriod.add(new Period(201800, "select"));
        qPeriod.add(new Period(201901, "January - March 2019"));
        qPeriod.add(new Period(201804, "October - December 2018"));
        qPeriod.add(new Period(201803, "July - September 2018"));
        qPeriod.add(new Period(201802, "April - June 2018"));
      //  qPeriod.add(new Period(201801, "January - March 2018"));

        PeriodAdapter adapter = new PeriodAdapter(this, android.R.layout.simple_spinner_dropdown_item, qPeriod);
        spinner_period.setAdapter(adapter);
        spinner_period.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (choice.matches("select")) {

                } else if (choice.matches("January - March 2019")) {
                    Period quarter = (Period) parent.getSelectedItem();
                    quarter.setSelected(true);
                    quarter.save();
                }else if (choice.matches("October - December 2018")) {
                    Period quarter = (Period) parent.getSelectedItem();
                    quarter.setSelected(true);
                    quarter.save();
                }else if (choice.matches("July - September 2018")) {
                    Period quarter = (Period) parent.getSelectedItem();
                    quarter.setSelected(true);
                    quarter.save();
                }else if (choice.matches("April - June 2018")) {
                    Period quarter = (Period) parent.getSelectedItem();
                    quarter.setSelected(true);
                    quarter.save();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void savePeriod(){

    }
/*
    private void savePeriod(List<Period> qPeriod) {
        Log.d("Saving quarter", "saving " + qPeriod.size());
        for (Period period : qPeriod) {
            period.save();
        }
    }*/

    /*facility level*/
    public void spinnerData_facilityLevel(Spinner spinner, final String choice) {

        List<KeyValue> keyvalue = new ArrayList<KeyValue>();
        keyvalue.add(new KeyValue(0,"Select"));
        keyvalue.add(new KeyValue(1,"level 6"));
        keyvalue.add(new KeyValue(2, "level 5"));
        keyvalue.add(new KeyValue(3, "level 4 with dialysis"));
        keyvalue.add(new KeyValue(4, "level 4"));
        keyvalue.add(new KeyValue(5, "level 3"));
        keyvalue.add(new KeyValue(6, "level 2"));


        //fill data in spinner
        ArrayAdapter<KeyValue> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, keyvalue);
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
           /*     Dimension2 d2 = new Dimension2();
                Dimension3 d3 = new Dimension3();
                Dimension4 d4 = new Dimension4();
                Dimension5 d5 = new Dimension5();
                Dimension6 d6 = new Dimension6();
                Dimension7 d7 = new Dimension7();
                Dimension8 d8 = new Dimension8();
                Dimension9 d9 = new Dimension9();
                Dimension10 d10 = new Dimension10();
                Dimension11_1 d11_1 = new Dimension11_1();
                Dimension11_2 d11_2 = new Dimension11_2();
                Dimension11_3 d11_3 = new Dimension11_3();
                Dimension11_4 d11_4 = new Dimension11_4();
                Dimension11_5 d11_5 = new Dimension11_5();
                Dimension11_6 d11_6 = new Dimension11_6();
                Dimension11_7 d11_7 = new Dimension11_7();
                Dimension11_8 d11_8 = new Dimension11_8();
                Dimension11_9 d11_9 = new Dimension11_9();
                Dimension11_10 d11_10 = new Dimension11_10();
                Dimension11_11 d11_11 = new Dimension11_11();
                Dimension11_12 d11_12 = new Dimension11_12();
                Dimension11_13 d11_13 = new Dimension11_13();
                Dimension11_14 d11_14 = new Dimension11_14();*/

                // here we change layout visibility again
                if(spinner_facilityLevel.getSelectedItem().toString().equals("select")){

                }else  if(spinner_facilityLevel.getSelectedItem().toString().equals("level 2")) {
                  //  d2.hide_unhide_level_2();
                }else  if(spinner_facilityLevel.getSelectedItem().toString().equals("level 3")) {
                   // d2.hide_unhide_level_3();
                }else  if(spinner_facilityLevel.getSelectedItem().toString().equals("level 4")) {
                   // d2.hide_unhide_level_4();
                }else  if(spinner_facilityLevel.getSelectedItem().toString().equals("level 5")) {
                  //  d2.hide_unhide_level_5();
                }else  if(spinner_facilityLevel.getSelectedItem().toString().equals("level 6")) {
                   // d2.hide_unhide_level_6();
                }
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