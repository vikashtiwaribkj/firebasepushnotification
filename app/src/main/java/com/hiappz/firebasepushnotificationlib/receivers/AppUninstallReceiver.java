package com.hiappz.firebasepushnotificationlib.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hiappz.firebasepushnotificationlib.helpers.LogHelper;

/**
 * Created by aj on 18/9/17.
 */

public class AppUninstallReceiver extends BroadcastReceiver {
    private final String TAG = "AppUninstallReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        LogHelper.d(TAG, "-->> onReceive -->> executed");
    }
}
