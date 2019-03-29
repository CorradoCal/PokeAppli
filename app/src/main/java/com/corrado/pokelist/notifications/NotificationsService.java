package com.corrado.pokelist.notifications;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Corrado on 27/03/2019.
 */
public class NotificationsService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() != null) {
            // Get message sent by Firebase
            String message = remoteMessage.getNotification().getBody();
            // Show message in console
            Log.i("TAG", message);
        }
    }
}
