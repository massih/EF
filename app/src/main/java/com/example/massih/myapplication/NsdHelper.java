package com.example.massih.myapplication;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Massih on 2015-09-11.
 */
public class NsdHelper {

    private Context zContext;
    NsdManager zNsdManager;
    NsdManager.ResolveListener zResolveListener;
    NsdManager.DiscoveryListener zDiscoveryListener;
    NsdManager.RegistrationListener zRegistrationListener;
    NsdServiceInfo zService;
    public String zServiceName = "ESMFAMIL:";

    public static final String SERVICE_TYPE = "_http._tcp.";
    public static final String TAG = "NsdHelper";


    public NsdHelper(Context context) {
        zContext = context;
        zNsdManager = (NsdManager)zContext.getSystemService(Context.NSD_SERVICE);
    }

    public void initializeNsd() {
        initializeResolveListener();
        initializeDiscoveryListener();
        initializeRegistrationListener();
    }

    public void initializeResolveListener() {
        zResolveListener = new NsdManager.ResolveListener() {

            @Override
            public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) {
                Log.e(TAG, "Resolve failed" + errorCode);
            }

            @Override
            public void onServiceResolved(NsdServiceInfo serviceInfo) {
                Log.e(TAG, "Resolve Succeeded. " + serviceInfo);


                if (serviceInfo.getServiceName().equals(zServiceName)) {
                    Log.d(TAG, "Same IP.");
                    return;
                }
                zService = serviceInfo;

            }
        };
    }

    public void initializeDiscoveryListener() {
        zDiscoveryListener = new NsdManager.DiscoveryListener() {

            @Override
            public void onDiscoveryStarted(String regType) {
                Log.d(TAG, "Service discovery started");
            }

            @Override
            public void onServiceFound(NsdServiceInfo service) {
                Log.d(TAG, "Service discovery success" + service);
                if (!service.getServiceType().equals(SERVICE_TYPE)) {
                    Log.d(TAG, "Unknown Service Type: " + service.getServiceType());
                } else if (service.getServiceName().equals(zServiceName)) {
                    Log.d(TAG, "Same machine: " + zServiceName);
                } else if (service.getServiceName().contains(zServiceName)){
                    Toast.makeText(zContext, "Found: "+service.getServiceName(), Toast.LENGTH_LONG).show();
                    zNsdManager.resolveService(service, zResolveListener);
                }
            }

            @Override
            public void onServiceLost(NsdServiceInfo service) {
                Log.e(TAG, "service lost" + service);
                if (zService == service) {
                    zService = null;
                }
            }

            @Override
            public void onDiscoveryStopped(String serviceType) {
                Log.i(TAG, "Discovery stopped: " + serviceType);
            }

            @Override
            public void onStartDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "Discovery failed: Error code:" + errorCode);
                zNsdManager.stopServiceDiscovery(this);
            }

            @Override
            public void onStopDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "Discovery failed: Error code:" + errorCode);
                zNsdManager.stopServiceDiscovery(this);
            }
        };
    }

    public void initializeRegistrationListener() {
        zRegistrationListener = new NsdManager.RegistrationListener() {

            @Override
            public void onServiceRegistered(NsdServiceInfo NsdServiceInfo) {
                zServiceName = NsdServiceInfo.getServiceName();
                Toast.makeText(zContext, "Registered: "+zServiceName , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRegistrationFailed(NsdServiceInfo arg0, int arg1) {
                Log.d(TAG,"registration failed"+ arg0.toString());
            }

            @Override
            public void onServiceUnregistered(NsdServiceInfo arg0) {
            }

            @Override
            public void onUnregistrationFailed(NsdServiceInfo serviceInfo, int errorCode) {
            }

        };
    }

    public void registerService(int port, String name) {
        NsdServiceInfo serviceInfo  = new NsdServiceInfo();
        serviceInfo.setPort(port);
        zServiceName += name;
        serviceInfo.setServiceName(zServiceName);
        serviceInfo.setServiceType(SERVICE_TYPE);

        zNsdManager.registerService(
                serviceInfo, NsdManager.PROTOCOL_DNS_SD, zRegistrationListener);

    }

    public void discoverServices() {
        zNsdManager.discoverServices(
                SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, zDiscoveryListener);
    }

    public void stopDiscovery() {
        zNsdManager.stopServiceDiscovery(zDiscoveryListener);
    }

    public NsdServiceInfo getChosenServiceInfo() {
        return zService;
    }

    public void tearDown() {
        zNsdManager.unregisterService(zRegistrationListener);
    }

}