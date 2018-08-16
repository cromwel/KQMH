package com.kqmh.app.kqmh.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kqmh.app.kqmh.Forms.Dimensions_List;
import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.SessionManager;

public class ViewFilesActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfiles);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Fetching Scores");
        progressDialog.setCancelable(false);

        Button complete = findViewById(R.id.bt_start_assessment);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

    }

    public void submit() {
        try{


        }
        catch (Exception e){
            e.printStackTrace();
        }

        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), Dimensions_List.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}