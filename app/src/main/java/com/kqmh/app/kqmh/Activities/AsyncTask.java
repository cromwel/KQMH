package com.kqmh.app.kqmh.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.Utils.SendDeviceDetails;

public class AsyncTask extends AppCompatActivity {
    private ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfiles);


        new SendDeviceDetails.execute("http://52.88.194.67:8080/IOTProjectServer/registerDevice", postData.toString());
    }
}
