package com.mnface.sdk;

import android.app.Application;

import com.immomo.mncertification.MNFCService;

public class MyApplication extends Application {
    private static final String APP_ID = "ed908f89453ca1793dc7da5fb32e1b30";//replace your appid here
    @Override
    public void onCreate() {
        super.onCreate();
        MNFCService.getInstance().init(this, APP_ID);
        MNFCService.getInstance().preloadResource();
    }

}
