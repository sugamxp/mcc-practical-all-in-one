package com.example.mccpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NotificationActivity extends AppCompatActivity {

  Button btn_five_second, btn_cancel;
  static String CHANNEL_ID = "1";
  AlarmManager alarmManager;
  PendingIntent pendingIntent;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notification);
    btn_five_second = findViewById(R.id.btn_five_second);
    btn_cancel = findViewById(R.id.btn_cancel);

    createNotificationChannel();

    btn_five_second.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        scheduleNotification(getNotification(), 5000);
     }
    });

    btn_cancel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        alarmManager.cancel(pendingIntent);
      }
    });
  }

  private void scheduleNotification(Notification notification, int delay) {

    Intent notificationIntent = new Intent(this, NotificationPublisher.class);
    notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
    notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
    pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

    long futureInMillis = SystemClock.elapsedRealtime();
//    long futureInMillis = System.currentTimeMillis() + delay;

    alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
    alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, 60000, pendingIntent);  }

  private Notification getNotification() {
    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_icon)
        .setContentTitle("MCC Practical")
        .setContentText("Hello World, Its Sugam!!!")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    return builder.build();
  }

  private void createNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      CharSequence name = "channel1";
      String description = "mcc pracs channel";
      int importance = NotificationManager.IMPORTANCE_DEFAULT;
      NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
      channel.setDescription(description);
      NotificationManager notificationManager = getSystemService(NotificationManager.class);
      notificationManager.createNotificationChannel(channel);
    }
  }
}
