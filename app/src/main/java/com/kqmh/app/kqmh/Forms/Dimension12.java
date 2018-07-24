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

import com.kqmh.app.kqmh.Activities.MainProgress;
import com.kqmh.app.kqmh.Adapters.ScoreOptionstAdapter;
import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.SessionManager;
import com.kqmh.app.kqmh.Utils.JSONFileParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Dimension12 extends AppCompatActivity {

    List<Spinner> spinnerList = new ArrayList<>();
    private ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_dimension12);

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

        Button next = findViewById(R.id.btn_dims);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dims_submit();
            }
        });


        try {
        } catch (Exception e) {
            e.printStackTrace();
            progressDialog.cancel();
        }
    }

    public void prev_submit() {
        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), Dimension11_List.class);
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
        Intent intent = new Intent(getBaseContext(), MainProgress.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


       // progressDialog.cancel();

}