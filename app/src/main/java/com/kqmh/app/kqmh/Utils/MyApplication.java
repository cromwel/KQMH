package com.kqmh.app.kqmh.Utils;

import android.app.Application;

import com.raizlabs.android.dbflow.config.DatabaseConfig;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(FlowConfig.builder(this)
                .addDatabaseConfig(DatabaseConfig.builder(MyDatabase.class)
                        .databaseName("AppDatabase")
                        .build())
                .build());
        FlowManager.init(new FlowConfig.Builder(this).build());
    }

}