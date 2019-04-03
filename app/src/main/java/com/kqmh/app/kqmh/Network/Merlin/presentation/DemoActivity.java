package com.kqmh.app.kqmh.Network.Merlin.presentation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.kqmh.app.kqmh.Forms.Assessment_Info;
import com.kqmh.app.kqmh.Models.AbstractOrgUnit;
import com.kqmh.app.kqmh.Models.DataElement;
import com.kqmh.app.kqmh.Models.DataElement_Table;
import com.kqmh.app.kqmh.Models.Option;
import com.kqmh.app.kqmh.Models.OrganisationUnit;
import com.kqmh.app.kqmh.Models.OrganisationUnit_Table;
import com.kqmh.app.kqmh.Models.Period;
import com.kqmh.app.kqmh.R;

import com.kqmh.app.kqmh.Network.Core.merlin.Bindable;
import com.kqmh.app.kqmh.Network.Core.merlin.Connectable;
import com.kqmh.app.kqmh.Network.Core.merlin.Disconnectable;
import com.kqmh.app.kqmh.Network.Core.merlin.Merlin;
import com.kqmh.app.kqmh.Network.Core.merlin.MerlinsBeard;
import com.kqmh.app.kqmh.Network.Core.merlin.NetworkStatus;
import com.kqmh.app.kqmh.Network.Merlin.connectivity.display.NetworkStatusDisplayer;
import com.kqmh.app.kqmh.Network.Merlin.presentation.base.MerlinActivity;
import com.kqmh.app.kqmh.SessionManager;
import com.kqmh.app.kqmh.Utils.AuthBuilder;
import com.kqmh.app.kqmh.Utils.UrlConstants;
import com.kqmh.app.kqmh.Utils.VolleySingleton;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DemoActivity extends MerlinActivity implements Connectable, Disconnectable, Bindable {

    private NetworkStatusDisplayer networkStatusDisplayer;
    private MerlinsBeard merlinsBeard;
    private View viewToAttachDisplayerTo;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Syncing data...");
        progressDialog.setCancelable(false);

        viewToAttachDisplayerTo = findViewById(R.id.displayerAttachableView);
        merlinsBeard = MerlinsBeard.from(this);
        networkStatusDisplayer = new NetworkStatusDisplayer(getResources(), merlinsBeard);

        findViewById(R.id.current_status).setOnClickListener(networkStatusOnClick);
        findViewById(R.id.bt_start_assessment).setOnClickListener(nextActivityOnClick);

    }

    private final View.OnClickListener networkStatusOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (merlinsBeard.isConnected()) {
                networkStatusDisplayer.displayPositiveMessage(R.string.current_status_network_connected, viewToAttachDisplayerTo);
                submit_file();
            } else {
                networkStatusDisplayer.displayNegativeMessage(R.string.current_status_network_disconnected, viewToAttachDisplayerTo);
            }
        }
    };

    private final View.OnClickListener nextActivityOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), Assessment_Info.class);
            startActivity(intent);
        }
    };

    @Override
    protected Merlin createMerlin() {
        return new Merlin.Builder()
                .withConnectableCallbacks()
                .withDisconnectableCallbacks()
                .withBindableCallbacks()
                .build(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerConnectable(this);
        registerDisconnectable(this);
        registerBindable(this);
    }

    @Override
    public void onBind(NetworkStatus networkStatus) {
        if (!networkStatus.isAvailable()) {
            onDisconnect();
        }
    }

    @Override
    public void onConnect() {
        networkStatusDisplayer.displayPositiveMessage(R.string.connected, viewToAttachDisplayerTo);
    }

    @Override
    public void onDisconnect() {
        networkStatusDisplayer.displayNegativeMessage(R.string.disconnected, viewToAttachDisplayerTo);
    }

    @Override
    protected void onPause() {
        super.onPause();
        networkStatusDisplayer.reset();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        networkStatusDisplayer = null;
    }

    public JSONObject toJSon() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String now = dateFormat.format(date);
        try {
            JSONObject dataset = new JSONObject();
            List<DataElement> elements = SQLite.select()
                    .from(DataElement.class)
                    .where(DataElement_Table.value.notEq("8"))
                    .queryList();

            String orgUnitId = new SessionManager(getBaseContext()).getSaveOrgUnitId();
            if(orgUnitId != null) {
                OrganisationUnit orgUnit = SQLite.select()
                        .from(OrganisationUnit.class).where(OrganisationUnit_Table.id.eq(orgUnitId)).querySingle();
                if (orgUnit != null) {
                    Log.d("Assigned id", orgUnit.getId());
                    dataset.put("orgUnit", orgUnit.getId());
                }
            }
            Period periodT = SQLite.select()
                    .from(Period.class).querySingle();
            JSONArray datavaluesarray = new JSONArray();

            for (DataElement dataElement : elements) {
                if (dataElement.getValue() != null) {
                    JSONObject dataElementObj = new JSONObject();
                    dataElementObj.put("dataElement", dataElement.getDataElementId());
                    dataElementObj.put("value", dataElement.getValue());
                    Log.d("score value", dataElement.getValue());
                    //dataElementObj.put("comment", dataElement.getComment());

                    datavaluesarray.put(dataElementObj);
                }
            }

            dataset.put("dataSet", "TA4FU3zu93l");
            dataset.put("completeDate", now);
            dataset.put("period", 201901);
            dataset.put("dataValues", datavaluesarray);
            Log.d("data", String.valueOf(dataset));
            return dataset;

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    public void submit_file() {
        Log.d("who", String.valueOf(toJSon()));
        OrganisationUnit orgUnit = SQLite.select()
                .from(OrganisationUnit.class)
                .querySingle();
       /* AbstractOrgUnit orgUnit = SQLite.select()
                .from(AbstractOrgUnit.class)
                .querySingle();*/
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
        }else{
            Toast.makeText(DemoActivity.this, "no organisation unit attached", Toast.LENGTH_SHORT).show();
        }

    }

    private void send(final String encoded, final JSONObject jsonObject) {
        progressDialog.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, UrlConstants.SEND_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());

                try {
                    if(response.has("status") && response.getString("status").equals("SUCCESS")){
                        Delete.table(DataElement.class);
                        Delete.table(Option.class);
                        Toast.makeText(DemoActivity.this, "data posted to server successfully", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), Assessment_Info.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }else{
                        Toast.makeText(DemoActivity.this, "Error : data posted to server failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(DemoActivity.this, "Error : data posted to server failed", Toast.LENGTH_SHORT).show();
                }
                closeProgressbar();
                //finish();
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

    private void closeProgressbar() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
