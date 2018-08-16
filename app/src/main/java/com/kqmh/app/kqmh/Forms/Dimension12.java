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

    EditText et_av_i1, et_av_i2, et_av_i3, et_av_i4, et_av_i5;
    EditText et_av_i6n, et_av_i13n, et_av_i23n, et_av_i30n, et_av_i31n, et_av_i7n, et_av_i17n, et_av_i27n;
    EditText et_av_i6d, et_av_i13d, et_av_i23d, et_av_i30d, et_av_i31d, et_av_i7d, et_av_i17d, et_av_i27d;
    TextView tv_avscore_i1, tv_avscore_i2, tv_avscore_i3, tv_avscore_i4, tv_avscore_i5, tv_avscore_i6, tv_avscore_i13, tv_avscore_i23, tv_avscore_i30, tv_avscore_i31, tv_avscore_i7, tv_avscore_i17, tv_avscore_i27;
    int int_et_av_i1, int_et_av_i2, int_et_av_i3, int_et_av_i4, int_et_av_i5;
    int int_et_av_i6n, int_et_av_i13n, int_et_av_i23n, int_et_av_i30n, int_et_av_i31n, int_et_av_i7n, int_et_av_i17n, int_et_av_i27n;
    int int_et_av_i6d, int_et_av_i13d, int_et_av_i23d, int_et_av_i30d, int_et_av_i31d, int_et_av_i7d, int_et_av_i17d, int_et_av_i27d;
    float float_et_av_i6, float_et_av_i13, float_et_av_i23, float_et_av_i30, float_et_av_i31, float_et_av_i7, float_et_av_i17, float_et_av_i27;


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

        et_av_i13n = (EditText) findViewById(R.id.av_i13n);
        et_av_i13d = (EditText) findViewById(R.id.av_i13d);
        tv_avscore_i13 = (TextView) findViewById(R.id.avscore_i13);

        et_av_i23n = (EditText) findViewById(R.id.av_i23n);
        et_av_i23d = (EditText) findViewById(R.id.av_i23d);
        tv_avscore_i23 = (TextView) findViewById(R.id.avscore_i23);

        et_av_i30n = (EditText) findViewById(R.id.av_i30n);
        et_av_i30d = (EditText) findViewById(R.id.av_i30d);
        tv_avscore_i30 = (TextView) findViewById(R.id.avscore_i30);

        et_av_i31n = (EditText) findViewById(R.id.av_i31n);
        et_av_i31d = (EditText) findViewById(R.id.av_i31d);
        tv_avscore_i31 = (TextView) findViewById(R.id.avscore_i31);

        et_av_i7n = (EditText) findViewById(R.id.av_i7n);
        et_av_i7d = (EditText) findViewById(R.id.av_i7d);
        tv_avscore_i7 = (TextView) findViewById(R.id.avscore_i7);

        et_av_i17n = (EditText) findViewById(R.id.av_i17n);
        et_av_i17d = (EditText) findViewById(R.id.av_i17d);
        tv_avscore_i17 = (TextView) findViewById(R.id.avscore_i17);

        et_av_i27n = (EditText) findViewById(R.id.av_i27n);
        et_av_i27d = (EditText) findViewById(R.id.av_i27d);
        tv_avscore_i27 = (TextView) findViewById(R.id.avscore_i27);

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
                        Log.v("EditText", et_av_i13n.getText().toString());
                        Log.v("EditText", et_av_i13d.getText().toString());
                        Log.v("EditText", et_av_i23n.getText().toString());
                        Log.v("EditText", et_av_i23d.getText().toString());
                        Log.v("EditText", et_av_i30n.getText().toString());
                        Log.v("EditText", et_av_i30d.getText().toString());
                        Log.v("EditText", et_av_i31n.getText().toString());
                        Log.v("EditText", et_av_i31d.getText().toString());

                        Log.v("EditText", et_av_i7n.getText().toString());
                        Log.v("EditText", et_av_i7d.getText().toString());
                        Log.v("EditText", et_av_i17n.getText().toString());
                        Log.v("EditText", et_av_i17d.getText().toString());
                        Log.v("EditText", et_av_i27n.getText().toString());
                        Log.v("EditText", et_av_i27d.getText().toString());

                        percentile_points();
                        days_points();
                        episodes_points();
                        fraction_percentile_points(tv_avscore_i6, et_av_i6n, et_av_i6d, 100, 75,50,25);
                        fraction_percentile_points(tv_avscore_i13, et_av_i13n, et_av_i13d, 100, 75,50,25);
                        fraction_percentile_points(tv_avscore_i23, et_av_i23n, et_av_i23d, 100, 75,50,25);
                        fraction_percentile_points(tv_avscore_i30, et_av_i30n, et_av_i30d, 100, 75,50,25);
                        fraction_percentile_points(tv_avscore_i31, et_av_i31n, et_av_i31d, 100, 75,50,25);
                        fraction_percentile_points(tv_avscore_i7, et_av_i7n, et_av_i7d, 90, 68,45,22);
                        fraction_percentile_points(tv_avscore_i17, et_av_i17n, et_av_i17d, 90, 68,45,22);
                        fraction_percentile_points(tv_avscore_i27, et_av_i27n, et_av_i27d, 90, 68,45,22);

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

    public void next_submit() {
        new SessionManager(getBaseContext()).setLoggedIn(true);
        Intent intent = new Intent(getBaseContext(), ProgressActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
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

    public void fraction_percentile_points(TextView textView, EditText numerator, EditText denominator, int p4, int p3, int p2, int p1) {
        try {
            int int_et_av_n = Integer.parseInt(numerator.getText().toString());
            int int_et_av_d = Integer.parseInt(denominator.getText().toString());

            float float_et_av = (((float) int_et_av_n / (float) int_et_av_d) * 100);

            if (float_et_av == p4) {
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
}