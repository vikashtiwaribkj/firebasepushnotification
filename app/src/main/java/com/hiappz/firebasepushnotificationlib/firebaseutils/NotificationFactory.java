package com.hiappz.firebasepushnotificationlib.firebaseutils;

import android.content.Context;

import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by aj on 14/9/17.
 */

public abstract interface NotificationFactory {
    public abstract void fireNotification(RemoteMessage remoteMessage, Context context);
    public abstract void sendNotification(Context context, Class activityClass);
}
