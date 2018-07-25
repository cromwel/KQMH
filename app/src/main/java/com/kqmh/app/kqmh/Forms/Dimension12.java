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

import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.SessionManager;

import java.util.ArrayList;
import java.util.List;

import android.widget.*;

public class Dimension12 extends AppCompatActivity {

    EditText et_av_i1, et_av_i2, et_av_i3, et_av_i4, et_av_i5/*, et_av_i6, et_av_i7, et_av_i8, et_av_i9, et_av_i10, et_av_i11,*/;
    TextView tv_avscore_i1, tv_avscore_i2,tv_avscore_i3, tv_avscore_i4, tv_avscore_i5;
    int int_et_av_i1, int_et_av_i2, int_et_av_i3, int_et_av_i4, int_et_av_i5;

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


            et_av_i1 = (EditText)findViewById(R.id.av_i1);
            tv_avscore_i1 = (TextView)findViewById(R.id.avscore_i1);

            et_av_i2 = (EditText)findViewById(R.id.av_i2);
            tv_avscore_i2 = (TextView)findViewById(R.id.avscore_i2);

            et_av_i3 = (EditText)findViewById(R.id.av_i3);
            tv_avscore_i3 = (TextView)findViewById(R.id.avscore_i3);

            et_av_i4 = (EditText)findViewById(R.id.av_i4);
            tv_avscore_i4 = (TextView)findViewById(R.id.avscore_i4);

            et_av_i5 = (EditText)findViewById(R.id.av_i5);
            tv_avscore_i5 = (TextView)findViewById(R.id.avscore_i5);

            Button validate = findViewById(R.id.btn_validate);
            validate.setOnClickListener(
                new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Log.v("EditText", et_av_i1.getText().toString());
                    Log.v("EditText", et_av_i2.getText().toString());
                    Log.v("EditText", et_av_i3.getText().toString());
                    Log.v("EditText", et_av_i4.getText().toString());
                    Log.v("EditText", et_av_i5.getText().toString());
                        percentile_points();
                        days_points();
                        episodes_points();
                        days2_points();
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


        public void percentile_points() {

            try {
                int_et_av_i1 = Integer.parseInt(et_av_i1.getText().toString());

                if (int_et_av_i1 < 21) {
                    tv_avscore_i1.setText(String.format("%s%%\n score = 0p", et_av_i1.getText().toString()));
                } else if (int_et_av_i1 > 20 && int_et_av_i1< 43) {
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
                } else if (int_et_av_i2 > 20 && int_et_av_i2< 43) {
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
                } else if (int_et_av_i3 <5) {
                    tv_avscore_i3.setText(String.format("%s day(s)\n score = 4p", et_av_i3.getText().toString()));
                }

            } catch (NumberFormatException nfe) {
                // Handle parse error.
            }
        }

        public void episodes_points(){
            try{
                int_et_av_i4 = Integer.parseInt(et_av_i4.getText().toString());

                if (int_et_av_i4 > 6) {
                    tv_avscore_i4.setText(String.format("%s day(s)\n score = 0p", et_av_i4.getText().toString()));
                } else if (int_et_av_i4 > 4 && int_et_av_i4 < 7) {
                    tv_avscore_i4.setText(String.format("%s day(s)\n score = 1p", et_av_i4.getText().toString()));
                } else if (int_et_av_i4 > 2 && int_et_av_i4 < 5) {
                    tv_avscore_i4.setText(String.format("%s day(s)\n score = 2p", et_av_i4.getText().toString()));
                } else if (int_et_av_i4 > 0 && int_et_av_i4 < 3) {
                    tv_avscore_i4.setText(String.format("%s day(s)\n score = 3p", et_av_i4.getText().toString()));
                } else if (int_et_av_i4 == 0) {
                    tv_avscore_i4.setText(String.format("%s day(s)\n score = 4p", et_av_i4.getText().toString()));
                }

        } catch (NumberFormatException nfe) {
        // Handle parse error.
    }
}

    public void days2_points(){
        try{
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

}