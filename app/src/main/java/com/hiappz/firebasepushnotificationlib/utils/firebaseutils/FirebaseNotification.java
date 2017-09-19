package com.hiappz.firebasepushnotificationlib.utils.firebaseutils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;
import com.hiappz.firebasepushnotificationlib.R;
import com.hiappz.firebasepushnotificationlib.helpers.ExceptionHelper;
import com.hiappz.firebasepushnotificationlib.helpers.LogHelper;
import com.hiappz.firebasepushnotificationlib.utils.UtilityConstant;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

/**
 * Created by aj on 14/9/17.
 */

/**
 * cid - campaign id received from backend
 * crid - campaign report id received from backend
 * id - id(navigationId) is the id which is required to open any particular activity like reservationId to open reservation detail activity, benefitId to open benefit detail activity
 * title - contains the title of the notification
 * icon - contains the icon to be displayed in notification tray
 * body - contains notification body
 * click_action - indicates the activity to be opened on click of notification
 */

public class FirebaseNotification implements NotificationFactory{
    private final String TAG = "FirebaseNotification";
    private static final int NOTIFICATION_ID = 100;

    private final String cidKey = "cid", cridKey = "crid", idKey = "id", titleKey = "title", bodyKey = "body", iconKey = "icon",
            clickActionKey = "click_action";

    private String idValue = null, titleValue = null, bodyValue = null, campaignIdValue, campaignReportIdValue,
            iconValue = null, clickActionValue = null;
    private int navigationId;
    private boolean isComingPushNotification = false;

    @Override
    public void fireNotification(RemoteMessage remoteMessage, Context context) {
//        notificationHelper = new FirebaseNotificationHelper.Builder().
        Map<String, String> dataPayload = null;

        LogHelper.d(TAG, "fireNotification: -->> executed");

        try {
            dataPayload = remoteMessage.getData();

            titleValue = dataPayload.get(titleKey);
            bodyValue = dataPayload.get(bodyKey);
            iconValue = dataPayload.get(iconKey);
            clickActionValue = dataPayload.get(clickActionKey);
            idValue = dataPayload.get(idKey);
            campaignIdValue = dataPayload.get(cidKey);
            campaignReportIdValue = dataPayload.get(cridKey);

            navigationId = Integer.valueOf(idValue);
        } catch (NumberFormatException e) {
            ExceptionHelper.handleNumberFormatException(TAG, e);
        } catch (Exception e) {
            ExceptionHelper.handleException(TAG, e);
        }

//        sendNotification(context, ActivityPushNotificationMain.class);

    }

    @Override
    public NotificationCompat.Builder setUpNotification(Context context) {
        Bitmap remote_picture = null;
        Bitmap largeIconBitmap = null;
        Uri defaultSoundUri = null;
        long[] pattern = {500, 500, 500, 500, 500};
        NotificationCompat.Builder notificationCompatBuilder = null;

        largeIconBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        notificationCompatBuilder = new NotificationCompat.Builder(context);

        notificationCompatBuilder.setLargeIcon(largeIconBitmap)
                .setContentTitle(titleValue != null ? titleValue: "")
                .setContentText(bodyValue != null ? bodyValue : "")
//                .setSubText("Notification SubText")
                .setColor(ContextCompat.getColor(context, R.color.colorPureWhite))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setVibrate(pattern);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            notificationCompatBuilder.setSmallIcon(R.mipmap.ic_launcher);
        } else {
            // Lollipop specific setColor method goes here.
            notificationCompatBuilder.setSmallIcon(R.drawable.notification_small_icon);
        }

        try {
            remote_picture = BitmapFactory.decodeStream((InputStream) new URL((iconValue)).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (iconValue != null && !iconValue.equals("")) {
            NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle()
                    .bigPicture(remote_picture);
//                    .setSummaryText("BigPicture Summary Text");

            notificationCompatBuilder.setStyle(bigPictureStyle);
        }

        return notificationCompatBuilder;
    }

    @Override
    public void sendNotification(Context context, NotificationCompat.Builder notificationCompatBuilder, Class activityClass) {

        notificationCompatBuilder = setUpNotification(context);
        isComingPushNotification = true;

        Intent actionIntent = new Intent(context, activityClass);
        actionIntent.putExtra(UtilityConstant.COME_FROM_PUSH_NOTIFICATION_KEY, isComingPushNotification);
        actionIntent.putExtra(UtilityConstant.CAMPAIGN_ID_KEY, campaignIdValue != null ? campaignIdValue : "");
        actionIntent.putExtra(UtilityConstant.CAMPAIGN_REPORT_ID_KEY, campaignReportIdValue != null ? campaignReportIdValue : "");
        actionIntent.putExtra(UtilityConstant.REQUIRED_UTILITY_ID_KEY, navigationId);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationCompatBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notificationCompatBuilder.build());
    }
}
