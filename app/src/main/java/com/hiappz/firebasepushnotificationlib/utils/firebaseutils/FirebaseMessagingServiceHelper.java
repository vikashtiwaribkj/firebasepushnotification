package com.hiappz.firebasepushnotificationlib.utils.firebaseutils;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.hiappz.firebasepushnotificationlib.helpers.ExceptionHelper;

/**
 * Created by aj on 14/9/17.
 */

public class FirebaseMessagingServiceHelper extends FirebaseMessagingService {
    private final String TAG = "FirebaseMsgServiceHlper";
    private NotificationFactory notificationFactory;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        try {
            notificationFactory = (NotificationFactory) Class.forName(FirebaseNotification.class.getName()).newInstance();
            notificationFactory.fireNotification(remoteMessage, getApplicationContext());
        } catch (InstantiationException e) {
            ExceptionHelper.handleInstantiationException(TAG, e);
        } catch (IllegalAccessException e) {
            ExceptionHelper.handleIllegalAccessException(TAG, e);
        } catch (ClassNotFoundException e) {
            ExceptionHelper.handleClassNotFoundException(TAG, e);
        }
    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onSendError(String s, Exception e) {
        super.onSendError(s, e);
    }
}
