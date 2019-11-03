package com.example.mccpractical;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationManagerCompat;

public class NotificationPublisher extends BroadcastReceiver {

  public static String NOTIFICATION_ID = "notification-id";
  public static String NOTIFICATION = "notification";

  public void onReceive(Context context, Intent intent) {

    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
    Notification notification = intent.getParcelableExtra(NOTIFICATION);
    int id = intent.getIntExtra(NOTIFICATION_ID, 0);
    notificationManager.notify(id, notification);
  }
}