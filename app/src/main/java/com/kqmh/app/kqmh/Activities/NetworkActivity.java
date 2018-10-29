package com.kqmh.app.kqmh.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kqmh.app.kqmh.Forms.Assessment_Info;
import com.kqmh.app.kqmh.Network.Core.merlin.Bindable;
import com.kqmh.app.kqmh.Network.Core.merlin.Connectable;
import com.kqmh.app.kqmh.Network.Core.merlin.Disconnectable;
import com.kqmh.app.kqmh.Network.Core.merlin.Merlin;
import com.kqmh.app.kqmh.Network.Core.merlin.MerlinsBeard;
import com.kqmh.app.kqmh.Network.Core.merlin.NetworkStatus;
import com.kqmh.app.kqmh.R;
import com.kqmh.app.kqmh.Network.Merlin.connectivity.display.NetworkStatusDisplayer;
import com.kqmh.app.kqmh.Network.Merlin.presentation.base.MerlinActivity;


public class NetworkActivity extends MerlinActivity implements Connectable, Disconnectable, Bindable {

    private NetworkStatusDisplayer networkStatusDisplayer;
    private MerlinsBeard merlinsBeard;
    private View viewToAttachDisplayerTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        viewToAttachDisplayerTo = findViewById(R.id.displayerAttachableView);
        merlinsBeard = MerlinsBeard.from(this);
        networkStatusDisplayer = new NetworkStatusDisplayer(getResources(), merlinsBeard);

        findViewById(R.id.current_status).setOnClickListener(networkStatusOnClick);
        findViewById(R.id.bt_start_assessment).setOnClickListener(nextActivityOnClick);
    }

    private final View.OnClickListener networkStatusOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (merlinsBeard.isConnected()) {
                networkStatusDisplayer.displayPositiveMessage(R.string.current_status_network_connected, viewToAttachDisplayerTo);
            } else {
                networkStatusDisplayer.displayNegativeMessage(R.string.current_status_network_disconnected, viewToAttachDisplayerTo);
            }
        }
    };

    private final View.OnClickListener wifiConnectedOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (merlinsBeard.isConnectedToWifi()) {
                networkStatusDisplayer.displayPositiveMessage(R.string.wifi_connected, viewToAttachDisplayerTo);
            } else {
                networkStatusDisplayer.displayNegativeMessage(R.string.wifi_disconnected, viewToAttachDisplayerTo);
            }
        }
    };

    private final View.OnClickListener mobileConnectedOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (merlinsBeard.isConnectedToMobileNetwork()) {
                networkStatusDisplayer.displayPositiveMessage(R.string.mobile_connected, viewToAttachDisplayerTo);
            } else {
                networkStatusDisplayer.displayNegativeMessage(R.string.mobile_disconnected, viewToAttachDisplayerTo);
            }
        }
    };

    private final View.OnClickListener networkSubtypeOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            networkStatusDisplayer.displayNetworkSubtype(viewToAttachDisplayerTo);
        }
    };

    private final View.OnClickListener nextActivityOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), Assessment_Info.class);
            startActivity(intent);
        }
    };

    @Override
    protected Merlin createMerlin() {
        return new Merlin.Builder()
                .withConnectableCallbacks()
                .withDisconnectableCallbacks()
                .withBindableCallbacks()
                .build(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerConnectable(this);
        registerDisconnectable(this);
        registerBindable(this);
    }

    @Override
    public void onBind(NetworkStatus networkStatus) {
        if (!networkStatus.isAvailable()) {
            onDisconnect();
        }
    }

    @Override
    public void onConnect() {
        networkStatusDisplayer.displayPositiveMessage(R.string.connected, viewToAttachDisplayerTo);
    }

    @Override
    public void onDisconnect() {
        networkStatusDisplayer.displayNegativeMessage(R.string.disconnected, viewToAttachDisplayerTo);
    }

    @Override
    protected void onPause() {
        super.onPause();
        networkStatusDisplayer.reset();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        networkStatusDisplayer = null;
    }
}
