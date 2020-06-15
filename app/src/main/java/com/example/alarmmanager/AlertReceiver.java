package com.example.alarmmanager;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);// khởi tạo kênh thông báo
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();// trình tạo thông báo
    // tạo ra title, icon...
        notificationHelper.getManager().notify(1, nb.build());// thông báo thay đổi
      // nb.build() =Notification
    }
}
