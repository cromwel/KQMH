package com.kqmh.app.kqmh.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kqmh.app.kqmh.Forms.Dimensions_List;
import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.SessionManager;
import com.kqmh.app.kqmh.Utils.AuthBuilder;

public class Welcome extends AppCompatActivity {
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        Button login_int = findViewById(R.id.bt_login_int);
        Button login_ex = findViewById(R.id.bt_login_ex);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Logging in...");
        progressDialog.setCancelable(false);

        login_int.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit_int();
            }
        });
        login_ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit_ex();
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

    private void closeProgressbar() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
