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
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_1, "Dimension 1", 0, 27));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_2,"Dimension 2", 0, 26));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_3,"Dimension 3", 0, 11));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_4,"Dimension 4", 0, 60));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_5,"Dimension 5", 0, 9));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_6,"Dimension 6", 0, 23));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_7,"Dimension 7", 0, 6));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_8,"Dimension 8", 0, 16));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_9,"Dimension 9", 0, 26));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_10,"Dimension 10", 0, 11));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_1,"Dimension 11_1", 0, 35));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_2,"Dimension 11_2", 0, 29));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_3,"Dimension 11_3", 0, 15));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_4,"Dimension 11_4", 0, 30));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_5,"Dimension 11_5", 0, 20));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_6, "Dimension 11_6",0, 18));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_7,"Dimension 11_7", 0, 25));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_8,"Dimension 11_8", 0, 30));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_9,"Dimension 11_9", 0, 29));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_10,"Dimension 11_10", 0, 93));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_11,"Dimension 11_11", 0, 46));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_12,"Dimension 11_12", 0, 18));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_13,"Dimension 11_13", 0, 13));
        progresses.add(new AssesmentProgress(AppConstants.DIMENSION_11_14,"Dimension 11_14", 0, 10));

        for (AssesmentProgress progress : progresses) {
            progress.save();
        }
    }

}
