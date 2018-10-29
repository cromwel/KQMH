package com.kqmh.app.kqmh.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kqmh.app.kqmh.Network.Core.merlin.Bindable;
import com.kqmh.app.kqmh.Network.Core.merlin.Connectable;
import com.kqmh.app.kqmh.Network.Core.merlin.Disconnectable;
import com.kqmh.app.kqmh.Network.Core.merlin.Merlin;

public abstract class MerlinActivity extends AppCompatActivity {

    // private DemoLogHandle logHandle;
    protected Merlin merlin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //logHandle = new DemoLogHandle();
        merlin = createMerlin();
    }

    protected abstract Merlin createMerlin();

    protected void registerConnectable(Connectable connectable) {
        merlin.registerConnectable(connectable);
    }

    protected void registerDisconnectable(Disconnectable disconnectable) {
        merlin.registerDisconnectable(disconnectable);
    }

    protected void registerBindable(Bindable bindable) {
        merlin.registerBindable(bindable);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Logger.attach(logHandle);
        merlin.bind();
    }

    @Override
    protected void onStop() {
        super.onStop();
        merlin.unbind();
        //Logger.detach(logHandle);
    }

}

