package com.mosangeet;

import android.app.Application;

import com.mosangeet.common.AppStartup;
import com.mosangeet.util.PreferenceUtil;


public class App extends Application {
    private static App mInstance;
    public static synchronized App getInstance() {
        return mInstance;
    }

    public PreferenceUtil getPreferencesUtility() {
        return PreferenceUtil.getInstance(App.this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        AppStartup.onAppStartup();
    }


}