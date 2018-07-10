package com.kqmh.app.kqmh.Forms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.SessionManager;

public class Dimension11_5 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_dimension11_5);

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

    }


    public void prev_submit() {
        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), Dimension11_4.class);
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
        Intent intent = new Intent(getBaseContext(), Dimension11_6.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}