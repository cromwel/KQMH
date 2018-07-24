package com.kqmh.app.kqmh.Forms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.kqmh.app.kqmh.Adapters.ScoreOptionstAdapter;
import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.SessionManager;
import com.kqmh.app.kqmh.Utils.JSONFileParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Dimension3 extends AppCompatActivity {

    List<Spinner> spinnerList = new ArrayList<>();
    private ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_dimension3);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Fetching Scores");
        progressDialog.setCancelable(false);

        Button prevDim = findViewById(R.id.btn_prev);
        prevDim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prev_submit();
            }
        });

        Button dims = findViewById(R.id.btn_dims);
        dims.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dims_submit();
            }
        });

        Button nextDim = findViewById(R.id.btn_next);
        nextDim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next_submit();
            }
        });



        for(int value=1;value<12;value++){
            Resources res  = getResources();
            String spinnerParse = String.format(res.getString(R.string.spinner_score),value);

            spinnerList.add((Spinner) findViewById(getResources().getIdentifier(spinnerParse,"id",getPackageName())));
            Log.d("Spinner Tag",spinnerList.toString()+"");
        }

        //spinnerList.add((Spinner) findViewById(R.id.spinner_score1));

        try {
            populateSpinners();
        } catch (Exception e) {
            e.printStackTrace();
            progressDialog.cancel();
        }

    }



    public void prev_submit() {
        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), Dimension2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void dims_submit() {
        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), Dimensions_List.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void next_submit() {
        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), Dimension4.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void populateSpinners() throws JSONException {
        progressDialog.show();
        String fromJsonFile = JSONFileParser.loadJSONFromAsset(getBaseContext());
        JSONObject fileObject = new JSONObject(fromJsonFile);
        JSONArray dataElements = fileObject.getJSONArray("dataSetElements");
        for (int i = 0; i < dataElements.length(); i++) {
            JSONObject jsonObject = dataElements.getJSONObject(i);
            JSONObject dataElement = jsonObject.getJSONObject("dataElement");
            String id = dataElement.getString("id");
            Log.d("data element", " " + i + " " + id);
            for (Spinner spinner : spinnerList) {
                if (spinner.getTag().toString().equals(id)) {
                    //Tag matches json id
                    JSONObject optionSet = dataElement.getJSONObject("optionSet");
                    JSONArray options = optionSet.getJSONArray("options");
                    List<String> optionsList = new ArrayList<>();
                    for(int j = 0; j < options.length(); j++){
                        optionsList.add(options.getJSONObject(j).getString("name"));
                    }
                    ScoreOptionstAdapter adapter = new ScoreOptionstAdapter(this, android.R.layout.simple_spinner_dropdown_item, optionsList);
                    spinner.setAdapter(adapter);
                    Log.d("Options size", " " + optionsList.size());

                }
            }
        }

        progressDialog.cancel();
    }
}