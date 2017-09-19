package com.hiappz.firebasepushnotificationlib.utils.firebaseutils;

import android.content.Context;
import android.support.v7.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by aj on 14/9/17.
 */

public abstract interface NotificationFactory {
    public abstract void fireNotification(RemoteMessage remoteMessage, Context context);
    public abstract void sendNotification(Context context, NotificationCompat.Builder notificationCompatBuilder, Class activityClass);
    public abstract NotificationCompat.Builder setUpNotification(Context context);
}
