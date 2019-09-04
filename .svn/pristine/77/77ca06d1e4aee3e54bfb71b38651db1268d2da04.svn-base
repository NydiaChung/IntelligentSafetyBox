package com.newland.intelligentsafetybox;

import android.app.Application;
import android.content.Context;

public class IntelligentSafetyBoxApplication extends Application {
    private static final String TAG = "IntelligentDaySystemApplication";

    public static Context mContext = null;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getInstance() {
        return mContext;
    }
}
