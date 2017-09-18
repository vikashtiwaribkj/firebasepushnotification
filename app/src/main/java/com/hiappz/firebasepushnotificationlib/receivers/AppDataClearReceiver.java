package com.hiappz.firebasepushnotificationlib.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hiappz.firebasepushnotificationlib.ActivityPushNotificationMain;
import com.hiappz.firebasepushnotificationlib.helpers.LogHelper;

/**
 * Created by aj on 18/9/17.
 */

public class AppDataClearReceiver extends BroadcastReceiver {
    private final String TAG = "AppDataClearReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        LogHelper.d(TAG, "-->> onReceive -->> executed");

        Intent intent1 = new Intent(context, ActivityPushNotificationMain.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}
