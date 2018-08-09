package com.kqmh.app.kqmh.Forms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.kqmh.app.kqmh.Adapters.ScoreOptionstAdapter;
import com.kqmh.app.kqmh.Adapters.ScoreOptionstAdapter2;
import com.kqmh.app.kqmh.Models.DataElement;
import com.kqmh.app.kqmh.Models.Option;
import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.SessionManager;
import com.kqmh.app.kqmh.Utils.AppConstants;
import com.kqmh.app.kqmh.Utils.JSONFileParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Dimension6 extends AppCompatActivity {
    List<Spinner> spinnerList = new ArrayList<>();
    List<DataElement> dataElementsList = new ArrayList<>();
    private ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_dimension6);

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



        for(int value=1;value<24;value++){
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
        Intent intent = new Intent(getBaseContext(), Dimension5.class);
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
        Intent intent = new Intent(getBaseContext(), Dimension7.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void populateSpinners() throws JSONException {
        progressDialog.show();
        String fromJsonFile = JSONFileParser.loadJSONFromAsset(getBaseContext(), "Requirements_Dim6.json");
        JSONObject fileObject = new JSONObject(fromJsonFile);
        JSONArray dataElements = fileObject.getJSONArray("dataSetElements");
        for (int i = 0; i < dataElements.length(); i++) {
            JSONObject jsonObject = dataElements.getJSONObject(i);
            JSONObject dataElement = jsonObject.getJSONObject("dataElement");
            String id = dataElement.getString("id");

            for (final Spinner spinner : spinnerList) {
                if (spinner.getTag().toString().equals(id)) {
                    //Build data element
                    DataElement element = new DataElement();
                    element.setEntity(AppConstants.DIMENSION_6);
                    element.setDataElementId(id);
                    dataElementsList.add(element);

                    //Tag matches json id
                    JSONObject optionSet = dataElement.getJSONObject("optionSet");
                    JSONArray options = optionSet.getJSONArray("options");
                    List<Option> optionList = new ArrayList<>();
                    Gson gson = new Gson();

                    Option selectOption = new Option(id,"","","Select");
                    optionList.add(selectOption);

                    for (int j = 0; j < options.length(); j++) {
                        Option option = gson.fromJson(options.getJSONObject(j).toString(), Option.class);
                        optionList.add(option);
                    }
                    ScoreOptionstAdapter2 adapter = new ScoreOptionstAdapter2(this, android.R.layout.simple_spinner_dropdown_item, optionList);
                    spinner.setAdapter(adapter);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            Option option = (Option) adapterView.getSelectedItem();
                            Log.d("selected option ", option.getName() + " code " + option.getCode());
                            Log.d("id", "" + spinner.getTag());
                            int position = spinnerList.indexOf(spinner);
                            try {
                                DataElement selectedElement = dataElementsList.get(position);
                                selectedElement.setCategory(option.getId());
                                selectedElement.setValue(option.getCode());
                                selectedElement.save();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                }
            }
        }

        progressDialog.cancel();
    }
}