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


public class SplashScreen extends AppCompatActivity {
    private ProgressBar progressBar;

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        SessionManager sessionManager = new SessionManager(getBaseContext());
        if(sessionManager.isLoggedIn()){
            Intent intent = new Intent(getBaseContext(), Dimensions_List.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else{
            Intent intent = new Intent(getBaseContext(), Welcome.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }

}
