package com.kqmh.app.kqmh.Forms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.kqmh.app.kqmh.Activities.ProgressActivity;
import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.SessionManager;

import java.util.ArrayList;
import java.util.List;

import android.widget.*;

public class Dimension12 extends AppCompatActivity {

    int int_et_av_i1, int_et_av_i2, int_et_av_i3, int_et_av_i4, int_et_av_i5;
    EditText et_av_i1, et_av_i2, et_av_i3, et_av_i4, et_av_i5;
    EditText et_av_i6n, et_av_i7n, et_av_i8n, et_av_i9n, et_av_i10n, et_av_i11n, et_av_i12n, et_av_i13n, et_av_i14n, et_av_i15n, et_av_i16n, et_av_i17n, et_av_i18n, et_av_i19n, et_av_i20n, et_av_i21n, et_av_i22n, et_av_i23n, et_av_i24n, et_av_i25n, et_av_i26n, et_av_i27n, et_av_i28n, et_av_i29n, et_av_i30n, et_av_i31n, et_av_i32n;
    EditText et_av_i6d, et_av_i7d, et_av_i8d, et_av_i9d, et_av_i10d, et_av_i11d, et_av_i12d, et_av_i13d, et_av_i14d, et_av_i15d, et_av_i16d, et_av_i17d, et_av_i18d, et_av_i19d, et_av_i20d, et_av_i21d, et_av_i22d, et_av_i23d, et_av_i24d, et_av_i25d, et_av_i26d, et_av_i27d, et_av_i28d, et_av_i29d, et_av_i30d, et_av_i31d, et_av_i32d;
    TextView tv_avscore_i1, tv_avscore_i2, tv_avscore_i3, tv_avscore_i4, tv_avscore_i5, tv_avscore_i6, tv_avscore_i7, tv_avscore_i8, tv_avscore_i9, tv_avscore_i10, tv_avscore_i11, tv_avscore_i12, tv_avscore_i13, tv_avscore_i14, tv_avscore_i15, tv_avscore_i16, tv_avscore_i17, tv_avscore_i18, tv_avscore_i19, tv_avscore_i20, tv_avscore_i21, tv_avscore_i22, tv_avscore_i23, tv_avscore_i24, tv_avscore_i25, tv_avscore_i26, tv_avscore_i27, tv_avscore_i28, tv_avscore_i29, tv_avscore_i30, tv_avscore_i31, tv_avscore_i32;



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

        Button nextDim = findViewById(R.id.btn_next);
        nextDim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next_submit();
            }
        });


        et_av_i1 = (EditText) findViewById(R.id.av_i1);
        tv_avscore_i1 = (TextView) findViewById(R.id.avscore_i1);
        et_av_i2 = (EditText) findViewById(R.id.av_i2);
        tv_avscore_i2 = (TextView) findViewById(R.id.avscore_i2);
        et_av_i3 = (EditText) findViewById(R.id.av_i3);
        tv_avscore_i3 = (TextView) findViewById(R.id.avscore_i3);
        et_av_i4 = (EditText) findViewById(R.id.av_i4);
        tv_avscore_i4 = (TextView) findViewById(R.id.avscore_i4);
        et_av_i5 = (EditText) findViewById(R.id.av_i5);
        tv_avscore_i5 = (TextView) findViewById(R.id.avscore_i5);
        et_av_i6n = (EditText) findViewById(R.id.av_i6n);
        et_av_i6d = (EditText) findViewById(R.id.av_i6d);
        tv_avscore_i6 = (TextView) findViewById(R.id.avscore_i6);
        et_av_i7n = (EditText) findViewById(R.id.av_i7n);
        et_av_i7d = (EditText) findViewById(R.id.av_i7d);
        tv_avscore_i7 = (TextView) findViewById(R.id.avscore_i7);
        et_av_i8n = (EditText) findViewById(R.id.av_i8n);
        et_av_i8d = (EditText) findViewById(R.id.av_i8d);
        tv_avscore_i8 = (TextView) findViewById(R.id.avscore_i8);
        et_av_i9n = (EditText) findViewById(R.id.av_i9n);
        et_av_i9d = (EditText) findViewById(R.id.av_i9d);
        tv_avscore_i9 = (TextView) findViewById(R.id.avscore_i9);
        et_av_i10n = (EditText) findViewById(R.id.av_i10n);
        et_av_i10d = (EditText) findViewById(R.id.av_i10d);
        tv_avscore_i10 = (TextView) findViewById(R.id.avscore_i10);
        et_av_i11n = (EditText) findViewById(R.id.av_i11n);
        et_av_i11d = (EditText) findViewById(R.id.av_i11d);
        tv_avscore_i11 = (TextView) findViewById(R.id.avscore_i11);
        et_av_i12n = (EditText) findViewById(R.id.av_i12n);
        et_av_i12d = (EditText) findViewById(R.id.av_i12d);
        tv_avscore_i12 = (TextView) findViewById(R.id.avscore_i12);
        et_av_i13n = (EditText) findViewById(R.id.av_i13n);
        et_av_i13d = (EditText) findViewById(R.id.av_i13d);
        tv_avscore_i13 = (TextView) findViewById(R.id.avscore_i13);
        et_av_i14n = (EditText) findViewById(R.id.av_i14n);
        et_av_i14d = (EditText) findViewById(R.id.av_i14d);
        tv_avscore_i14 = (TextView) findViewById(R.id.avscore_i14);
        et_av_i15n = (EditText) findViewById(R.id.av_i15n);
        et_av_i15d = (EditText) findViewById(R.id.av_i15d);
        tv_avscore_i15 = (TextView) findViewById(R.id.avscore_i15);
        et_av_i16n = (EditText) findViewById(R.id.av_i16n);
        et_av_i16d = (EditText) findViewById(R.id.av_i16d);
        tv_avscore_i16 = (TextView) findViewById(R.id.avscore_i16);
        et_av_i17n = (EditText) findViewById(R.id.av_i17n);
        et_av_i17d = (EditText) findViewById(R.id.av_i17d);
        tv_avscore_i17 = (TextView) findViewById(R.id.avscore_i17);
        et_av_i18n = (EditText) findViewById(R.id.av_i18n);
        et_av_i18d = (EditText) findViewById(R.id.av_i18d);
        tv_avscore_i18 = (TextView) findViewById(R.id.avscore_i18);
        et_av_i19n = (EditText) findViewById(R.id.av_i19n);
        et_av_i19d = (EditText) findViewById(R.id.av_i19d);
        tv_avscore_i19 = (TextView) findViewById(R.id.avscore_i19);
        et_av_i20n = (EditText) findViewById(R.id.av_i20n);
        et_av_i20d = (EditText) findViewById(R.id.av_i20d);
        tv_avscore_i20 = (TextView) findViewById(R.id.avscore_i20);
        et_av_i21n = (EditText) findViewById(R.id.av_i21n);
        et_av_i21d = (EditText) findViewById(R.id.av_i21d);
        tv_avscore_i21 = (TextView) findViewById(R.id.avscore_i21);
        et_av_i22n = (EditText) findViewById(R.id.av_i22n);
        et_av_i22d = (EditText) findViewById(R.id.av_i22d);
        tv_avscore_i22 = (TextView) findViewById(R.id.avscore_i22);
        et_av_i23n = (EditText) findViewById(R.id.av_i23n);
        et_av_i23d = (EditText) findViewById(R.id.av_i23d);
        tv_avscore_i23 = (TextView) findViewById(R.id.avscore_i23);
        et_av_i24n = (EditText) findViewById(R.id.av_i24n);
        et_av_i24d = (EditText) findViewById(R.id.av_i24d);
        tv_avscore_i24 = (TextView) findViewById(R.id.avscore_i24);
        et_av_i25n = (EditText) findViewById(R.id.av_i25n);
        et_av_i25d = (EditText) findViewById(R.id.av_i25d);
        tv_avscore_i25 = (TextView) findViewById(R.id.avscore_i25);
        et_av_i26n = (EditText) findViewById(R.id.av_i26n);
        et_av_i26d = (EditText) findViewById(R.id.av_i26d);
        tv_avscore_i26 = (TextView) findViewById(R.id.avscore_i26);
        et_av_i27n = (EditText) findViewById(R.id.av_i27n);
        et_av_i27d = (EditText) findViewById(R.id.av_i27d);
        tv_avscore_i27 = (TextView) findViewById(R.id.avscore_i27);
        et_av_i28n = (EditText) findViewById(R.id.av_i28n);
        et_av_i28d = (EditText) findViewById(R.id.av_i28d);
        tv_avscore_i28 = (TextView) findViewById(R.id.avscore_i28);
        et_av_i29n = (EditText) findViewById(R.id.av_i29n);
        et_av_i29d = (EditText) findViewById(R.id.av_i29d);
        tv_avscore_i29 = (TextView) findViewById(R.id.avscore_i29);
        et_av_i30n = (EditText) findViewById(R.id.av_i30n);
        et_av_i30d = (EditText) findViewById(R.id.av_i30d);
        tv_avscore_i30 = (TextView) findViewById(R.id.avscore_i30);
        et_av_i31n = (EditText) findViewById(R.id.av_i31n);
        et_av_i31d = (EditText) findViewById(R.id.av_i31d);
        tv_avscore_i31 = (TextView) findViewById(R.id.avscore_i31);


        Button validate = findViewById(R.id.btn_validate);
        validate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v("EditText", et_av_i1.getText().toString());
                        Log.v("EditText", et_av_i2.getText().toString());
                        Log.v("EditText", et_av_i3.getText().toString());
                        Log.v("EditText", et_av_i4.getText().toString());
                        Log.v("EditText", et_av_i5.getText().toString());
                        Log.v("EditText", et_av_i6n.getText().toString());
                        Log.v("EditText", et_av_i6d.getText().toString());
                        Log.v("EditText", et_av_i7n.getText().toString());
                        Log.v("EditText", et_av_i7d.getText().toString());
                        Log.v("EditText", et_av_i8n.getText().toString());
                        Log.v("EditText", et_av_i8d.getText().toString());
                        Log.v("EditText", et_av_i9n.getText().toString());
                        Log.v("EditText", et_av_i9d.getText().toString());
                        Log.v("EditText", et_av_i10n.getText().toString());
                        Log.v("EditText", et_av_i10d.getText().toString());
                        Log.v("EditText", et_av_i11n.getText().toString());
                        Log.v("EditText", et_av_i11d.getText().toString());
                        Log.v("EditText", et_av_i12n.getText().toString());
                        Log.v("EditText", et_av_i12d.getText().toString());
                        Log.v("EditText", et_av_i13n.getText().toString());
                        Log.v("EditText", et_av_i13d.getText().toString());
                        Log.v("EditText", et_av_i14n.getText().toString());
                        Log.v("EditText", et_av_i14d.getText().toString());
                        Log.v("EditText", et_av_i15n.getText().toString());
                        Log.v("EditText", et_av_i15d.getText().toString());
                        Log.v("EditText", et_av_i16n.getText().toString());
                        Log.v("EditText", et_av_i16d.getText().toString());
                        Log.v("EditText", et_av_i17n.getText().toString());
                        Log.v("EditText", et_av_i17d.getText().toString());
                        Log.v("EditText", et_av_i18n.getText().toString());
                        Log.v("EditText", et_av_i18d.getText().toString());
                        Log.v("EditText", et_av_i19n.getText().toString());
                        Log.v("EditText", et_av_i19d.getText().toString());
                        Log.v("EditText", et_av_i20n.getText().toString());
                        Log.v("EditText", et_av_i20d.getText().toString());
                        Log.v("EditText", et_av_i21n.getText().toString());
                        Log.v("EditText", et_av_i21d.getText().toString());
                        Log.v("EditText", et_av_i22n.getText().toString());
                        Log.v("EditText", et_av_i22d.getText().toString());
                        Log.v("EditText", et_av_i23n.getText().toString());
                        Log.v("EditText", et_av_i23d.getText().toString());
                        Log.v("EditText", et_av_i24n.getText().toString());
                        Log.v("EditText", et_av_i24d.getText().toString());
                        Log.v("EditText", et_av_i26n.getText().toString());
                        Log.v("EditText", et_av_i26d.getText().toString());
                        Log.v("EditText", et_av_i27n.getText().toString());
                        Log.v("EditText", et_av_i27d.getText().toString());
                        Log.v("EditText", et_av_i28n.getText().toString());
                        Log.v("EditText", et_av_i28d.getText().toString());
                        Log.v("EditText", et_av_i29n.getText().toString());
                        Log.v("EditText", et_av_i29d.getText().toString());
                        Log.v("EditText", et_av_i30n.getText().toString());
                        Log.v("EditText", et_av_i30d.getText().toString());
                        Log.v("EditText", et_av_i31n.getText().toString());
                        Log.v("EditText", et_av_i31d.getText().toString());



                        percentile_points();
                        days_points();
                        episodes_points();

                        fraction_percentile_points(tv_avscore_i6, et_av_i6n, et_av_i6d, 100, 75,50,25);
                        fraction_percentile_points(tv_avscore_i13, et_av_i13n, et_av_i13d, 100, 75,50,25);
                        fraction_percentile_points(tv_avscore_i14, et_av_i14n, et_av_i14d, 100, 75,50,25);
                        fraction_percentile_points(tv_avscore_i23, et_av_i23n, et_av_i23d, 100, 75,50,25);
                        fraction_percentile_points(tv_avscore_i30, et_av_i30n, et_av_i30d, 100, 75,50,25);
                        fraction_percentile_points(tv_avscore_i31, et_av_i31n, et_av_i31d, 100, 75,50,25);
                        fraction_percentile_points(tv_avscore_i7, et_av_i7n, et_av_i7d, 90, 68,45,22);
                        fraction_percentile_points(tv_avscore_i17, et_av_i17n, et_av_i17d, 90, 68,45,22);
                        fraction_percentile_points(tv_avscore_i27, et_av_i27n, et_av_i27d, 90, 68,45,22);
                        fraction_percentile_points(tv_avscore_i11, et_av_i11n, et_av_i11d, 15, 11.4,7.5,3.8);

                        fraction_percent_points(tv_avscore_i8, et_av_i8n, et_av_i8d, 8.9, 7.6,6.4,5);
                        fraction_percent_points(tv_avscore_i15, et_av_i15n, et_av_i15d, 8.9, 7.6,6.4,5.1);
                        fraction_percent_points(tv_avscore_i16, et_av_i16n, et_av_i16d, 8.9, 7.6,6.4,5.1);
                        fraction_percent_points(tv_avscore_i21, et_av_i21n, et_av_i21d, 8.9, 7.6,6.4,5.1);
                        fraction_percent_points(tv_avscore_i29, et_av_i29n, et_av_i29d, 8.9, 7.6,6.4,5.1);
                        fraction_percent_points(tv_avscore_i9, et_av_i9n, et_av_i9d, 1.76, 1.6,1.26,1);
                        fraction_percent_points(tv_avscore_i12, et_av_i12n, et_av_i12d, 1.76, 1.6,1.26,1);
                        fraction_percent_points(tv_avscore_i18, et_av_i18n, et_av_i18d, 1.76, 1.6,1.26,1);
                        fraction_percent_points(tv_avscore_i22, et_av_i22n, et_av_i22d, 1.76, 1.6,1.26,1);
                        fraction_percent_points(tv_avscore_i10, et_av_i10n, et_av_i10d, 5.4, 4.4,3.9,3);
                        fraction_percent_points(tv_avscore_i26, et_av_i26n, et_av_i26d, 17.6, 15.1,12.6,10.1);

                        fraction_thousands_points(tv_avscore_i19, et_av_i19n, et_av_i19d, 17.6, 15.1,12.6,10.1);
                        fraction_thousands_points(tv_avscore_i20, et_av_i20n, et_av_i20d, 21.1, 18.1,15.1,12);



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
        startActivity(intent);
    }

    public void next_submit() {
        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), ProgressActivity.class);
        startActivity(intent);
    }


    public void percentile_points() {

        try {
            int_et_av_i1 = Integer.parseInt(et_av_i1.getText().toString());

            if (int_et_av_i1 < 21) {
                tv_avscore_i1.setText(String.format("%s%%\n score = 0p", et_av_i1.getText().toString()));
            } else if (int_et_av_i1 > 20 && int_et_av_i1 < 43) {
                tv_avscore_i1.setText(String.format("%s%%\n score = 1p", et_av_i1.getText().toString()));
            } else if (int_et_av_i1 > 42 && int_et_av_i1 < 64) {
                tv_avscore_i1.setText(String.format("%s%%\n score = 2p", et_av_i1.getText().toString()));
            } else if (int_et_av_i1 > 63 && int_et_av_i1 < 85) {
                tv_avscore_i1.setText(String.format("%s%%\n score = 3p", et_av_i1.getText().toString()));
            } else if (int_et_av_i1 > 84) {
                tv_avscore_i1.setText(String.format("%s%%\n score = 4p", et_av_i1.getText().toString()));
            }

            int_et_av_i2 = Integer.parseInt(et_av_i2.getText().toString());

            if (int_et_av_i2 < 21) {
                tv_avscore_i2.setText(String.format("%s%%\n score = 0p", et_av_i2.getText().toString()));
            } else if (int_et_av_i2 > 20 && int_et_av_i2 < 43) {
                tv_avscore_i2.setText(String.format("%s%%\n score = 1p", et_av_i2.getText().toString()));
            } else if (int_et_av_i2 > 42 && int_et_av_i2 < 64) {
                tv_avscore_i2.setText(String.format("%s%%\n score = 2p", et_av_i2.getText().toString()));
            } else if (int_et_av_i2 > 63 && int_et_av_i2 < 85) {
                tv_avscore_i2.setText(String.format("%s%%\n score = 3p", et_av_i2.getText().toString()));
            } else if (int_et_av_i2 > 84) {
                tv_avscore_i2.setText(String.format("%s%%\n score = 4p", et_av_i2.getText().toString()));
            }

        } catch (NumberFormatException nfe) {
            // Handle parse error.
        }
    }

    public void days_points() {

        try {
            int_et_av_i3 = Integer.parseInt(et_av_i3.getText().toString());

            if (int_et_av_i3 > 7) {
                tv_avscore_i3.setText(String.format("%s day(s)\n score = 0p", et_av_i3.getText().toString()));
            } else if (int_et_av_i3 == 7) {
                tv_avscore_i3.setText(String.format("%s day(s)\n score = 1p", et_av_i3.getText().toString()));
            } else if (int_et_av_i3 == 6) {
                tv_avscore_i3.setText(String.format("%s day(s)\n score = 2p", et_av_i3.getText().toString()));
            } else if (int_et_av_i3 == 5) {
                tv_avscore_i3.setText(String.format("%s day(s)\n score = 3p", et_av_i3.getText().toString()));
            } else if (int_et_av_i3 < 5) {
                tv_avscore_i3.setText(String.format("%s day(s)\n score = 4p", et_av_i3.getText().toString()));
            }


            int_et_av_i5 = Integer.parseInt(et_av_i5.getText().toString());

            if (int_et_av_i5 > 6) {
                tv_avscore_i5.setText(String.format("%s day(s)\n score = 0p", et_av_i5.getText().toString()));
            } else if (int_et_av_i5 > 4 && int_et_av_i5 < 7) {
                tv_avscore_i5.setText(String.format("%s day(s)\n score = 1p", et_av_i5.getText().toString()));
            } else if (int_et_av_i5 > 2 && int_et_av_i5 < 5) {
                tv_avscore_i5.setText(String.format("%s day(s)\n score = 2p", et_av_i5.getText().toString()));
            } else if (int_et_av_i5 > 0 && int_et_av_i5 < 3) {
                tv_avscore_i5.setText(String.format("%s day(s)\n score = 3p", et_av_i5.getText().toString()));
            } else if (int_et_av_i5 == 0) {
                tv_avscore_i5.setText(String.format("%s day(s)\n score = 4p", et_av_i5.getText().toString()));
            }


        } catch (NumberFormatException nfe) {
            // Handle parse error.
        }
    }

    public void episodes_points() {
        try {
            int_et_av_i4 = Integer.parseInt(et_av_i4.getText().toString());

            if (int_et_av_i4 > 6) {
                tv_avscore_i4.setText(String.format("%s episode(s)\n score = 0p", et_av_i4.getText().toString()));
            } else if (int_et_av_i4 > 4 && int_et_av_i4 < 7) {
                tv_avscore_i4.setText(String.format("%s episode(s)\n score = 1p", et_av_i4.getText().toString()));
            } else if (int_et_av_i4 > 2 && int_et_av_i4 < 5) {
                tv_avscore_i4.setText(String.format("%s episode(s)\n score = 2p", et_av_i4.getText().toString()));
            } else if (int_et_av_i4 > 0 && int_et_av_i4 < 3) {
                tv_avscore_i4.setText(String.format("%s episode(s)\n score = 3p", et_av_i4.getText().toString()));
            } else if (int_et_av_i4 == 0) {
                tv_avscore_i4.setText(String.format("%s episode(s)\n score = 4p", et_av_i4.getText().toString()));
            }

        } catch (NumberFormatException nfe) {
            // Handle parse error.
        }
    }

    public void fraction_percentile_points(TextView textView, EditText numerator, EditText denominator, double p4, double p3, double p2, double p1) {
        try {
            int int_et_av_n = Integer.parseInt(numerator.getText().toString());
            int int_et_av_d = Integer.parseInt(denominator.getText().toString());

            float float_et_av = (((float) int_et_av_n / (float) int_et_av_d) * 100);

            if (float_et_av >= p4) {
                textView.setText(String.format("%.1f%% \n score = 4p", float_et_av));
            } else if (float_et_av < p4 && float_et_av >= p3) {
                textView.setText(String.format("%.1f%% \n score = 3p", float_et_av));
            } else if (float_et_av < p3 && float_et_av >= p2) {
                textView.setText(String.format("%.1f%% \n score = 2p", float_et_av));
            } else if (float_et_av < p2 && float_et_av >= p1) {
                textView.setText(String.format("%.1f%% \n score = 1p", float_et_av));
            } else if (float_et_av < p1) {
                textView.setText(String.format("%.1f%% \n score = 0p", float_et_av));
            }
        } catch(NumberFormatException e) {
            // Handle parse error.
            Log.e("Dimension12", e.getMessage());
            e.printStackTrace();
        }
    }

    public void fraction_percent_points(TextView textView, EditText numerator, EditText denominator, double p4, double p3, double p2, double p1) {
        try {
            int int_et_av_n = Integer.parseInt(numerator.getText().toString());
            int int_et_av_d = Integer.parseInt(denominator.getText().toString());

            float float_et_av = (((float) int_et_av_n / (float) int_et_av_d) * 100);

            if (float_et_av >= p4) {
                textView.setText(String.format("%.1f%% \n score = 0p", float_et_av));
            } else if (float_et_av < p4 && float_et_av >= p3) {
                textView.setText(String.format("%.1f%% \n score = 1p", float_et_av));
            } else if (float_et_av < p3 && float_et_av >= p2) {
                textView.setText(String.format("%.1f%% \n score = 2p", float_et_av));
            } else if (float_et_av < p2 && float_et_av >= p1) {
                textView.setText(String.format("%.1f%% \n score = 3p", float_et_av));
            } else if (float_et_av < p1) {
                textView.setText(String.format("%.1f%% \n score = 4p", float_et_av));
            }
        } catch(NumberFormatException e) {
            // Handle parse error.
            Log.e("Dimension12", e.getMessage());
            e.printStackTrace();
        }
    }

    public void fraction_thousands_points(TextView textView, EditText numerator, EditText denominator, double p4, double p3, double p2, double p1) {
        try {
            int int_et_av_n = Integer.parseInt(numerator.getText().toString());
            int int_et_av_d = Integer.parseInt(denominator.getText().toString());

            float float_et_av = (((float) int_et_av_n / (float) int_et_av_d) * 1000);

            if (float_et_av >= p4) {
                textView.setText(String.format("%.0f per 1000 live births\n score = 0p", float_et_av));
            } else if (float_et_av < p4 && float_et_av >= p3) {
                textView.setText(String.format("%.0f per 1000 live births\n score = 1p", float_et_av));
            } else if (float_et_av < p3 && float_et_av >= p2) {
                textView.setText(String.format("%.0f per 1000 live births\n score = 2p", float_et_av));
            } else if (float_et_av < p2 && float_et_av >= p1) {
                textView.setText(String.format("%.0f per 1000 live births\n score = 3p", float_et_av));
            } else if (float_et_av < p1) {
                textView.setText(String.format("%.0f per 1000 live births\n score = 4p", float_et_av));
            }
        } catch(NumberFormatException e) {
            // Handle parse error.
            Log.e("Dimension12", e.getMessage());
            e.printStackTrace();
        }
    }
}