package com.cricket.crickais.freecode;

import android.app.Application;
import com.onesignal.OneSignal;
public class ApplicationClass extends Application {
    private static final String ONESIGNAL_APP_ID = "4418ebde-5334-4f78-920d-8e793f1699ac";

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }
}