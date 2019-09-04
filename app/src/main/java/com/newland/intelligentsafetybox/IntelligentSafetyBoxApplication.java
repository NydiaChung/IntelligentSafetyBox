package com.newland.intelligentsafetybox;

import android.app.Application;
import android.content.Context;

import com.newland.intelligentsafetybox.utils.CrashHandlerUtil;
import com.newland.intelligentsafetybox.utils.LogUtil;

public class IntelligentSafetyBoxApplication extends Application {
    private static final String TAG = "IntelligentDaySystemApplication";

    public static Context mContext = null;
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d(TAG, "onCreate");
        mContext = getApplicationContext();
        CrashHandlerUtil.getmInstance().init(mContext);
    }

    public static Context getInstance() {
        return mContext;
    }
}
