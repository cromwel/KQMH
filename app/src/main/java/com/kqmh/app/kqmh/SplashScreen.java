package com.kqmh.app.kqmh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.kqmh.app.kqmh.Activities.Login;
import com.kqmh.app.kqmh.Activities.Welcome;
import com.kqmh.app.kqmh.Forms.Assessment_Info;
import com.kqmh.app.kqmh.Forms.Dimensions_List;
import com.kqmh.app.kqmh.Models.AssesmentProgress;
import com.kqmh.app.kqmh.Utils.AppConstants;

import java.util.ArrayList;
import java.util.List;


public class SplashScreen extends AppCompatActivity {
    private ProgressBar progressBar;

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        SessionManager sessionManager = new SessionManager(getBaseContext());
        if (sessionManager.isLoggedIn()) {
            Intent intent = new Intent(getBaseContext(), Dimensions_List.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            saveProgress();
            Intent intent = new Intent(getBaseContext(), Welcome.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }

    private void saveProgress() {
        List<AssesmentProgress> progresses = new ArrayList<>();
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_1, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_2, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_3, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_4, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_5, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_6, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_7, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_8, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_9, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_10, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_1, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_2, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_3, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_4, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_5, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_6, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_7, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_8, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_9, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_10, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_11, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_12, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_13, 0, 0));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_14, 0, 0));

        for (AssesmentProgress progress : progresses) {
            progress.save();
        }
    }

}
