package com.kqmh.app.kqmh.Forms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kqmh.app.kqmh.Adapters.ScoreOptionstAdapter2;
import com.kqmh.app.kqmh.Models.AssesmentProgress;
import com.kqmh.app.kqmh.Models.AssesmentProgress_Table;
import com.kqmh.app.kqmh.Models.DataElement;
import com.kqmh.app.kqmh.Models.DataElement_Table;
import com.kqmh.app.kqmh.Models.Option;
import com.kqmh.app.kqmh.Models.Option_Table;
import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.SessionManager;
import com.kqmh.app.kqmh.Utils.AppConstants;
import com.kqmh.app.kqmh.Utils.JSONFileParser;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Dimension2 extends AppCompatActivity {

    List<Spinner> spinnerList = new ArrayList<>();
    List<DataElement> dataElementsList = new ArrayList<>();
    AssesmentProgress assesmentProgress;
    private ProgressDialog progressDialog;
    private ProgressBar progressBar;
    private TextView progressText;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_dimension2);
        assesmentProgress = SQLite.select().from(AssesmentProgress.class).where(AssesmentProgress_Table.assessment.eq(AppConstants.DIMENSION_2)).querySingle();

        // Get the widgets reference from XML layout
        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        final TextView tv = (TextView) findViewById(R.id.tv);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Fetching Scores");
        progressDialog.setCancelable(false);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next_submit();
            }
        });

        for (int value = 200; value < 226; value++) {
            Resources res = getResources();
            String spinnerParse = String.format(res.getString(R.string.spinner_score), value);

            spinnerList.add((Spinner) findViewById(getResources().getIdentifier(spinnerParse, "id", getPackageName())));
        }

        if (assesmentProgress != null) {
            assesmentProgress.setMax(spinnerList.size());
            assesmentProgress.update();
        }
        setUpProgress();

        //spinnerList.add((Spinner) findViewById(R.id.spinner_score1));

        try {
            setSelected();
        } catch (Exception e) {
            e.printStackTrace();
            progressDialog.cancel();
        }

    }

    @Override
    protected void onDestroy() {
        if (assesmentProgress != null) {
            assesmentProgress.setProgress(progressBar.getProgress());
            assesmentProgress.update();
        }
        super.onDestroy();
    }

    private void setUpProgress() {
        View v = findViewById(R.id.progress);
        progressBar = v.findViewById(R.id.pb);
        progressText = v.findViewById(R.id.tv);
        Toast.makeText(getBaseContext(), "Questions " + spinnerList.size(), Toast.LENGTH_LONG).show();
        progressBar.setMax(spinnerList.size());
        progressText.setText(String.format(Locale.getDefault(), "%d/%d", progressBar.getProgress(), progressBar.getMax()));
    }

    public void prev_submit() {
        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), Dimension1.class);
        startActivity(intent);
    }

    public void dims_submit() {
        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), Dimensions_List.class);
        startActivity(intent);
    }

    public void next_submit() {
        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), Dimension3.class);
        startActivity(intent);
    }

    private void populateSpinners() throws JSONException {
        progressDialog.show();
        String fromJsonFile = JSONFileParser.loadJSONFromAsset(getBaseContext(), "Requirements_Dim2.json");
        JSONObject fileObject = new JSONObject(fromJsonFile);
        JSONArray dataElements = fileObject.getJSONArray("dataSetElements");
        for (int i = 0; i < dataElements.length(); i++) {
            JSONObject jsonObject = dataElements.getJSONObject(i);
            JSONObject dataElement = jsonObject.getJSONObject("dataElement");
            String id = dataElement.getString("id");

            for (final Spinner spinner : spinnerList) {
                if (spinner.getTag().toString().equals(id)) {
                    //Build data element
                    Log.d("IDDDS", "Dataelement" + id + " tag " + spinner.getTag().toString());
                    DataElement element = new DataElement();
                    element.setEntity(AppConstants.DIMENSION_2);
                    element.setDataElementId(id);
                    element.save();
                    dataElementsList.add(element);

                    //Tag matches json id
                    JSONObject optionSet = dataElement.getJSONObject("optionSet");
                    JSONArray options = optionSet.getJSONArray("options");
                    List<Option> optionList = new ArrayList<>();
                    Gson gson = new Gson();

                    final Option selectOption = new Option("", "", "Select");
                    optionList.add(selectOption);

                    for (int j = 0; j < options.length(); j++) {
                        Option option = gson.fromJson(options.getJSONObject(j).toString(), Option.class);
                        optionList.add(option);
                    }
                    ScoreOptionstAdapter2 adapter = new ScoreOptionstAdapter2(this, android.R.layout.simple_spinner_dropdown_item, optionList);
                    spinner.setAdapter(adapter);
                    id = spinner.getTag().toString();
                    Log.d("Spinner id", id);
                    for (int counter = 0; counter < optionList.size(); counter++) {
                        Option selectedOption = optionList.get(counter);
                        DataElement element1 = SQLite.select()
                                .from(DataElement.class)
                                .where(DataElement_Table.dataElementId.eq(id))
                                .querySingle();
                        if (element1 != null && selectedOption.getId().equals(element1.getCategory())) {
                            Log.d("counter", String.valueOf(counter));
                            Log.d("not null", "" + element1.toString() + " id " + element1.getDataElementId() + "value " + element1.getValue());
                            spinner.setSelection(optionList.indexOf(selectedOption));
                            //progressBar.setProgress(progressBar.getProgress() + 1);
                        }
                    }

                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            Option option = (Option) adapterView.getSelectedItem();
                            int position = spinnerList.indexOf(spinner);
                            try {
                                DataElement selectedElement = SQLite.select()
                                        .from(DataElement.class)
                                        .where(DataElement_Table.dataElementId.eq(spinner.getTag().toString()))
                                        .querySingle();
                                //Check if null
                                if (selectedElement != null) {
                                    Log.d("option", "nit null " + selectedElement.getDataElementId() + " " + selectedElement.getValue());
                                    selectedElement.setCategory(option.getId());
                                    selectedElement.setValue(option.getCode());
                                    if (selectedElement.exists()) {
                                        selectedElement.update();

                                    } else {
                                        selectedElement.save();

                                    }
                                } else {
                                    Log.d("option", "selected is null");
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    break;
                }

            }
        }

        progressDialog.cancel();
    }

    private void populate() throws JSONException {
        progressDialog.show();
        String fromJsonFile = JSONFileParser.loadJSONFromAsset(getBaseContext(), "Requirements_Dim2.json");
        JSONObject fileObject = new JSONObject(fromJsonFile);
        JSONArray dataElements = fileObject.getJSONArray("dataSetElements");
        SessionManager sessionManager = new SessionManager(getBaseContext());
        for (int i = 0; i < dataElements.length(); i++) {
            JSONObject jsonObject = dataElements.getJSONObject(i);
            JSONObject dataElement = jsonObject.getJSONObject("dataElement");
            String id = dataElement.getString("id");
            for (final Spinner spinner : spinnerList) {
                if (spinner.getTag().toString().equals(id)) {
                    DataElement element2 = SQLite.select()
                            .from(DataElement.class)
                            .where(DataElement_Table.dataElementId.eq(spinner.getTag().toString())).querySingle();
                    if (element2 == null) {
                        DataElement element = new DataElement();
                        element.setEntity(AppConstants.DIMENSION_2);
                        element.setDataElementId(spinner.getTag().toString());
                        element.save();
                    }

                    //Add options
                    JSONObject optionSet = dataElement.getJSONObject("optionSet");
                    JSONArray options = optionSet.getJSONArray("options");
                    Gson gson = new Gson();

                    Option selectOption = SQLite.select()
                            .from(Option.class)
                            .where(Option_Table.parentId.eq(spinner.getTag().toString()))
                            .querySingle();
                    if (selectOption == null) {
                        selectOption = new Option("DEF01", "8", "Select");
                        selectOption.setParentId(spinner.getTag().toString());
                        if (!selectOption.exists()) {
                            selectOption.save();
                        }
                    }

                    if (!sessionManager.isOptionsSaved2()) {
                        for (int j = 0; j < options.length(); j++) {
                            Option option = gson.fromJson(options.getJSONObject(j).toString(), Option.class);
                            option.setParentId(spinner.getTag().toString());
                            option.save();
                        }
                    } else {
                        Log.d("error", "save error");
                    }
                    List<Option> optionList = SQLite.select()
                            .from(Option.class)
                            .where(Option_Table.parentId.eq(spinner.getTag().toString()))
                            .queryList();
                    ScoreOptionstAdapter2 adapter = new ScoreOptionstAdapter2(this, android.R.layout.simple_spinner_dropdown_item, optionList);

                    spinner.setAdapter(adapter);
                    if (element2 != null) {
                        for (Option option : optionList) {
                            if (option.isSelected()) {
                                if (element2.getValue() != null && element2.getValue().equals(option.getCode()) && !option.getCode().equals("8")) {
                                    Log.d("Found selected", "id" + option.getParentId() + " val " + option.getName() + " code " + option.getCode() + " element " + element2.getValue());
                                    spinner.setSelection(optionList.indexOf(option));
                                    progressBar.setProgress(progressBar.getProgress() + 1);
                                    progressText.setText(String.format(Locale.getDefault(), "%d/%d", progressBar.getProgress(), progressBar.getMax()));
                                }

                            }
                        }
                    }

                }
            }
        }
        sessionManager.setKeySavedOptions(true);
        progressDialog.cancel();
    }

    private void setSelected() {
        for (int i = 0; i < spinnerList.size(); i++) {
            final Spinner spinner = spinnerList.get(i);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Option option = (Option) adapterView.getSelectedItem();
                    option.setSelected(true);
                    option.save();
                    DataElement element = SQLite.select()
                            .from(DataElement.class)
                            .where(DataElement_Table.dataElementId.eq(option.getParentId()))
                            .querySingle();
                    if (element != null) {
                        if (option.getCode().equals("8")) {
                            if (element.getValue() != null && !element.getValue().equals("8")) {
                                //Means its a downgrade
                                progressBar.setProgress(progressBar.getProgress() - 1);
                                progressText.setText(String.format(Locale.getDefault(), "%d/%d", progressBar.getProgress(), progressBar.getMax()));
                            }
                        } else {
                            if (!element.getValue().equals(option.getCode()) && element.getValue().equals("8")) {
                                //Means its not the same option selected again and the previous value was 'select' , therefore upgrade.
                                progressBar.setProgress(progressBar.getProgress() + 1);
                                progressText.setText(String.format(Locale.getDefault(), "%d/%d", progressBar.getProgress(), progressBar.getMax()));
                            }
                        }
                        element.setValue(option.getCode());
                        element.setCategory(option.getId());
                        element.save();

                        Log.d("saved", element.getValue());

                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
        try {
            populate();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void onLevelSelected(final String choice){
        if(choice == "1"){

        }

    }
}