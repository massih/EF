package com.example.massih.myapplication;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;

/**
 * Created by Massih on 2015-09-11.
 */
public class NsdHelper {

    private Context zContext;
    NsdManager zNsdManager;
    NsdManager.ResolveListener zResolveListener;
    NsdManager.DiscoveryListener zDiscoveryListener;
    NsdManager.RegistrationListener zRegistrationListener;


    public static final String SERVICE_TYPE = "_http._tcp.";
    public static final String TAG = "NsdHelper";


    public NsdHelper(Context context) {
        zContext = context;
        zNsdManager = (NsdManager)zContext.getSystemService(Context.NSD_SERVICE);
    }

    private void initializeDiscoveryListener(){
        zDiscoveryListener = new NsdManager.DiscoveryListener(){
            @Override
            public void onStartDiscoveryFailed(String serviceType, int errorCode) {

            }

            @Override
            public void onStopDiscoveryFailed(String serviceType, int errorCode) {

            }

            @Override
            public void onDiscoveryStarted(String serviceType) {

            }

            @Override
            public void onDiscoveryStopped(String serviceType) {

            }

            @Override
            public void onServiceFound(NsdServiceInfo serviceInfo) {

            }

            @Override
            public void onServiceLost(NsdServiceInfo serviceInfo) {

            }
        };
    }
}
