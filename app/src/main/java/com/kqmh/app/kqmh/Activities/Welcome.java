package com.kqmh.app.kqmh.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.SessionManager;

public class Welcome extends AppCompatActivity {
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        Button login_int = findViewById(R.id.bt_login_int);
        Button login_ex = findViewById(R.id.bt_login_ex);
        Button login_chmt = findViewById(R.id.bt_login_chmt);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Logging in...");
        progressDialog.setCancelable(false);

        login_ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit_ex();
            }
        });
        login_chmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit_chmt();
            }
        });
        login_int.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit_int();
            }
        });

    }

    private void submit_int() {
        closeProgressbar();
        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), Login.class);
        startActivity(intent);
    }

    private void submit_ex() {
        closeProgressbar();
        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), LoginEx.class);
        startActivity(intent);
    }

    private void submit_chmt() {
        closeProgressbar();
        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), LoginCHMT.class);
        startActivity(intent);
    }

    private void closeProgressbar() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
