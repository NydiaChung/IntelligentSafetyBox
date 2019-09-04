package com.newland.intelligentsafetybox.utils;

import android.app.Activity;
import android.widget.Toast;

public class ToastUtil {
    private static String TAG = "ToastUtil";
    public static void showToast(final Activity activity, final String message) {
        if ("main".equals(Thread.currentThread().getName())) {
            LogUtil.d(TAG, "is in main thread");
        } else {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    LogUtil.d(TAG, "not in main thread");
                    Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
